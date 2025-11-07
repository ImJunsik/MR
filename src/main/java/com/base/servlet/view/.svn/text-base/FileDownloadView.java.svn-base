package com.base.servlet.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 첨부파일 다운로드를 위한 전용 View class 이다.
 * 해당 view를 사용하기 위해서는 Controller 클래스내 메소드에서 view 클래스(FileDownloadView)를 리턴해야 한다.
 *
 * @author 조용상
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 *
 * @see com.common.file.web.AttachFileController#download(String, HttpServletRequest)
 */
public class FileDownloadView extends AbstractView {
    private final Logger logger = Logger.getLogger(this.getClass());

    public FileDownloadView() {
        setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (model.get("downloadFile") != null) {
            File file = (File)model.get("downloadFile");

            String originalFilename = (String)model.get("originalFilename");
            originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
            originalFilename = originalFilename.replaceAll("\\+", "%20");

            response.setContentType(getContentType());
            response.setContentLength((int)file.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" +originalFilename+ "\";");

            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } finally {
                if(fis != null){
                    try{
                        fis.close();
                    } catch(Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
            out.flush();
        }
        else if(model.get("appDownloadFile") != null){
        	File file = (File)model.get("appDownloadFile");

            String originalFilename = (String)model.get("originalFilename");
            originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
            originalFilename = originalFilename.replaceAll("\\+", "%20");

            response.setContentType(getContentType());
            response.setContentLength((int)file.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" +originalFilename+ "\";");

            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } finally {
                if(fis != null){
                    try{
                        fis.close();
                    } catch(Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
            out.flush();
        }
    }
}