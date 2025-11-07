package com.common.file.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.yaml.snakeyaml.util.UriEncoder;

import com.base.servlet.BaseController;
import com.base.servlet.view.FileDownloadView;
import com.base.util.IsOperDistinc;
import com.common.file.domain.AttachFile;
import com.common.file.service.AttachFileService;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.io.InputStream;




/**
 * 첨부파일 다운로드 및 첨부파일 관련 Front-End의 요청 처리를 담당한다.
 *
 * @author 조용상
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
@Controller
@RequestMapping(value = "/")
public class AttachFileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	 
    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    protected XMLConfiguration appConfig;
    
    private final String UPLOAD_PATH = "D:/MR/MR_FILE/";		//운영 서버
    //private final String UPLOAD_PATH = "D:\MR\MR_FILE/";		//개발서버

    /**
     * 첨부파일 다운로드를 한다.
     * @param code 첨부파일 코드
     * @param request HttpServletRequest 요청 객체
     * @return FileDownloadView or DocumentView
     */
    @RequestMapping("/download.do")
    public ModelAndView download(@RequestParam String drawMngNo, HttpServletRequest request) {
        //View view = request.getRequestURI().endsWith("/download.do") ? new FileDownloadView() : new DocumentView();
        View view = new FileDownloadView();
        ModelAndView modelAndView = new ModelAndView(view);

        if (StringUtils.isNotEmpty(drawMngNo)) {
        	AttachFile attachFile = null;
        	if(IsOperDistinc.m_bOper)
        		attachFile = attachFileService.getAttachFileDrawMngNo(drawMngNo);
        	else
        		attachFile = attachFileService.getAttachFileDrawMngDevNo(drawMngNo);
        	
            modelAndView.addObject("downloadFile", attachFile.getAttachedFile());
            modelAndView.addObject("originalFilename", attachFile.getName());
            return modelAndView;
        }
        return modelAndView;
    }
    
    /**
     * 도면관리 시스템에서 MR시스템의 첨부파일 다운로드를 한다.
     * @param code 첨부파일 코드
     * @param request HttpServletRequest 요청 객체
     * @return FileDownloadView or DocumentView
     */
    @RequestMapping("/download2.do") 
    public void download2(@RequestParam String drawMngNo,
                          HttpServletResponse response) throws IOException {

        logger.info("download2.do 호출 - drawMngNo: " + drawMngNo);

        if (StringUtils.isNotEmpty(drawMngNo)) {
            AttachFile attachFile = null;

            try {
                
                logger.info("DB 조회");
                attachFile = attachFileService.getAttachFileDrawMngNo(drawMngNo);
                
                logger.info("attachFile 조회 결과: " + (attachFile != null ? "존재" : "null"));

                if (attachFile != null && attachFile.getAttachedFile() != null) {
                    File file = attachFile.getAttachedFile();
                    
                    logger.info("파일 경로: " + file.getAbsolutePath());
                    logger.info("파일 존재 여부: " + file.exists());
                    logger.info("파일 크기: " + file.length());
                    logger.info("파일명: " + attachFile.getName());

                    if (!file.exists()) {
                        logger.error("파일이 존재하지 않음: " + file.getAbsolutePath());
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("File not found");
                        return;
                    }

                    // 파일 다운로드 응답 헤더 설정
                    String encodedFileName = URLEncoder.encode(attachFile.getName(), "UTF-8")
                                            .replaceAll("\\+", "%20");
                    
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + encodedFileName + "\"");
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) file.length()); // int로 캐스팅
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Expires", "0");

                    logger.info("파일 스트리밍 시작");

                    // 파일 스트리밍 처리
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = new FileInputStream(file);
                        out = response.getOutputStream();

                        byte[] buffer = new byte[8192]; // 버퍼 크기 증가
                        int bytesRead;
                        long totalBytesRead = 0;
                        
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                            totalBytesRead += bytesRead;
                        }
                        
                        out.flush();
                        logger.info("파일 다운로드 완료 - 전송된 바이트: " + totalBytesRead);
                        
                    } catch (IOException e) {
                        logger.error("파일 스트리밍 중 오류 발생", e);
                        throw e;
                    } finally {
                        if (in != null) try { in.close(); } catch (IOException ignore) {}
                        if (out != null) try { out.close(); } catch (IOException ignore) {}
                    }
                } else {
                    logger.warn("첨부파일 정보가 없음");
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    response.getWriter().write("No file information");
                }
            } catch (Exception e) {
                logger.error("download2.do 처리 중 오류 발생", e);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Internal server error: " + e.getMessage());
            }
        } else {
            logger.warn("drawMngNo 파라미터 없음");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("drawMngNo parameter is required");
        }
    }
    
    //@RequestMapping("/fileUploadAjax.do")    //yoo 팝업 창에서의 매핑 오류 발생 하여 다중 매핑으로 수정
    @RequestMapping(value = {"/fileUploadAjax.do", "/popup/fileUploadAjax.do"})
    public Model fileUploadAjax(@RequestParam("filePath") MultipartFile filePath
    							,String localAddr
    							,String empNo
    							,String plantNo
    							,String mrNo
    							,Model model) {
    	
    	logger.info("modeClass : " + filePath);
    	logger.info("파일이름 : " + filePath.getOriginalFilename());
    	logger.info("파일크기 : " + filePath.getSize());
    	logger.info("empNo : " + empNo);
    	logger.info("plantNo : " + plantNo);
    	logger.info("mrNo : " + mrNo);
    	
    	String res = null;
    	
    	String fileStatus = saveFile(filePath);
    	
    	logger.info("업로드결과 : " + fileStatus);
    	
    	String url = "http://mrdev.oilbank.co.kr:443/edms/fi/other/inUploadFromMR.action"; // 개발
    	if(IsOperDistinc.m_bOperDatabaseConfig){
    		url = "http://edims.oilbank.co.kr/edms/fi/other/inUploadFromMR.action"; // 운영
        }
    	
    	logger.info("URL : " + url);
    	
    	if("OK".equals(fileStatus)){
    		String doc_seq = null;
			try {
				doc_seq = addFileForAttachment(url,
						UPLOAD_PATH + filePath.getOriginalFilename(),
						filePath.getOriginalFilename(),
						empNo,
						plantNo,
						mrNo,
						mrNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		logger.info("doc_seq=" + doc_seq);
    		res = doc_seq;
    		model.addAttribute("doc_seq",res);
    	}
    	
    	if(res != null && res != ""){
    		
    		File file = new File(UPLOAD_PATH + filePath.getOriginalFilename());

    		if(file.delete()){
    			logger.info("파일삭제 성공");
    		}else{
    			logger.info("파일삭제 실패");
    		}
    	} else {
    		model.addAttribute("doc_seq",null);
    	}
        
        return model;
    }
    
    
    
    @RequestMapping("/requestRegister.do")    
    public Model requestRegister(String requser_id
    							,String receiverid
    							,String mr_code
    							,String doc_seqList
    							,String remark
    							,Model model) {

    	String wf_id = "";

    	String url = "http://172.17.25.51:443/edms/wf/RequestMR.action"; // 개발
    	if(IsOperDistinc.m_bOperDatabaseConfig){
    		url = "http://edims.oilbank.co.kr/edms/wf/RequestMR.action"; // 운영
        }
    	
		try {
			wf_id = requestRegister(url,
					requser_id, 
					receiverid,
					mr_code, 
					doc_seqList, 
					remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("wf_id=" + wf_id);

        
        return model;
    }
    
    private String saveFile(MultipartFile file){
        
        File saveFile = new File(UPLOAD_PATH,file.getOriginalFilename());

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return "OK";
    }
    
    public static String addFileForAttachment(String uploadUrl, 
    		String filePath,
    		String fileNm,
    		String user_id,
    		String com_code,
    		String mr_code,
    		String mr_file_id) throws Exception {
        int BUFFER_SIZE = 4096;
        
        File uploadFile = new File(filePath);
 
        if (!uploadFile.exists()) {
        	System.out.println("Not exist") ;
        	return "" ;
        }
        System.out.println("File to upload: " + filePath);
 
        URL url = new URL(uploadUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("fileDTO.org_file", UriEncoder.encode(fileNm));
        httpConn.setRequestProperty("fileDTO.com_org_file", UriEncoder.encode(fileNm));
        httpConn.setRequestProperty("fileDTO.i_emp_id", (user_id == null) ? "admin" : user_id);
        httpConn.setRequestProperty("fileDTO.com_code", (com_code == null) ? "" : com_code);
        httpConn.setRequestProperty("fileDTO.mr_code", (mr_code == null) ? "" : mr_code);
        httpConn.setRequestProperty("fileDTO.mr_file_id", (mr_file_id == null) ? "" : mr_file_id);


        httpConn.setRequestProperty("Content-Type", "image/png");
        
        // opens output stream of the HTTP connection for writing data
        OutputStream outputStream = httpConn.getOutputStream();
 
        // Opens input stream of the file for reading data
        FileInputStream inputStream = new FileInputStream(uploadFile);
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        System.out.println("Start writing data...");
 
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
 
        System.out.println("Data was written.");
        outputStream.flush();
        outputStream.close();
        inputStream.close();
 
        String doc_seq = "" ;
        
        // always check HTTP response code from server
        int responseCode = httpConn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
        	doc_seq = httpConn.getHeaderField("doc_seq") ;
        }
        
        return doc_seq ;
    }
    
    public static String requestRegister(String uploadUrl, String requser_id, 
    		String receiverid, 
    		String mr_code, 
    		String doc_seqList, 
    		String remark) throws Exception {
 
        URL url = new URL(uploadUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        //httpConn.setRequestMethod("POST");
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("workFlowFormDTO.doc_seq", doc_seqList);
        httpConn.setRequestProperty("workFlowFormDTO.workflowDTO.wf_kind", "RMR");
      //httpConn.setRequestProperty("workFlowFormDTO.workflowAppDTO.remark", remark);
        httpConn.setRequestProperty("workFlowFormDTO.workflowAppDTO.remark", UriEncoder.encode(remark));
        httpConn.setRequestProperty("workFlowFormDTO.appList.app_user", requser_id);
        httpConn.setRequestProperty("workFlowFormDTO.documentDTO.mr_code", mr_code);
        httpConn.setRequestProperty("workFlowFormDTO.workflowDTO.requser_id", requser_id);
        httpConn.setRequestProperty("workFlowFormDTO.workflowAppDTO.app_user", requser_id);
        httpConn.setRequestProperty("workFlowFormDTO.workflowDTO.emp_id", receiverid);

        String wf_id = null ;
        
        // always check HTTP response code from server
        int responseCode = httpConn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
        	wf_id = httpConn.getHeaderField("wf_id") ;
        }      	
        
        return wf_id ;
    } 
}