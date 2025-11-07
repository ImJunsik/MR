package com.base.servlet.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.base.util.SpringApplicationContext;

/**
 * 첨부파일 바로보기를 위한 전용 View class 이다.
 * 해당 view를 사용하기 위해서는 Controller 클래스내 메소드에서 view 클래스(DocumentView)를 리턴해야 한다.
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
public class DocumentView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (model.get("downloadFile") != null) {
            File file = (File)model.get("downloadFile");
            String originalFilename = (String)model.get("originalFilename");
            String extension = FilenameUtils.getExtension(originalFilename);

            String mimeType = null;
            XMLConfiguration appConfig = SpringApplicationContext.getBean(XMLConfiguration.class);
            List<HierarchicalConfiguration> mimePappings = appConfig.configurationsAt("mime.mime-mapping");
            for (HierarchicalConfiguration mimePapping : mimePappings) {
                if (extension.equals(mimePapping.getString("extension"))) {
                    mimeType = mimePapping.getString("mime-type");
                    break;
                }
            }

            // app-config.xml에 첨부파일 확장자에 해당하는 mime-type이 없다면 일반적인 파일 다운로드를 시킨다.
            if (mimeType == null) {
                mimeType = getContentType();
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(originalFilename, "utf-8") + "\";");
            } else {
                response.setHeader("Content-Disposition", "filename=\"" + URLEncoder.encode(originalFilename, "utf-8") + "\";");
            }

            response.setContentType(mimeType);
            response.setContentLength((int)file.length());

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