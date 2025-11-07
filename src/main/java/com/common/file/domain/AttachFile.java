package com.common.file.domain;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.base.domain.Domain;
import com.common.file.exception.AttachedFileNotFoundException;

/**
 * 첨부파일 정보를 담고있는 도메인 클래스.
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
 * @see com.examples.domain.Employee#getAttachFiles()
 */
public class AttachFile extends Domain {
    public static final Format CONVERSION = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);
    private String serverPath;
    private String code;
    private String path;
    private String name;
    private String convertName;
    private long size;

    private boolean deleted;
    private MultipartFile multipartFile;



    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    /**
     * @return 첨부파일 코드
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 첨부파일 코드
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 첨부파일이 저장된 경로
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path 첨부파일이 저장될 경로
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return 첨부파일 원본 이름
     */
    public String getName() {
        return isMultipartFileNotEmpty() ? multipartFile.getOriginalFilename() : name;
    }

    /**
     * @param name 첨부파일 원본 이름
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 디스크에 저장된 첨부파일 변환 이름
     */
    public String getConvertName() {
        if (isMultipartFileNotEmpty()  && convertName == null) {
            StringBuilder builder = new StringBuilder();
            String filename = FilenameUtils.removeExtension(getName());
            String extension = FilenameUtils.getExtension(getName());

            builder.append(filename).append("_").append(CONVERSION.format(new Date())).append(".").append(extension);
            convertName = builder.toString();
        }
        return convertName;
    }

    /**
     * 첨부파일 저장시 첨부파일 명 중복으로인해 기존 첨부파일을 덮어쓰지 않도록
     * 첨부파일의 이름을 변경한다.
     * @param convertName 디스크에 저장된 첨부파일 변환 이름
     */
    public void setConvertName(String convertName) {
        this.convertName = convertName;
    }

    /**
     * @return 첨부파일 크기
     */
    public long getSize() {
        return isMultipartFileNotEmpty() ? getMultipartFile().getSize() : size;
    }

    /**
     * @param size 첨부파일 크기
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return 첨부파일이 삭제되었는지 여부
     * @see com.common.file.service.impl.AttachFileServiceImpl#saveAttachFiles(java.util.List)
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted 첨부파일이 삭제되었는지 여부
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return 웹으로부터 전송된 첨부파일
     */
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    /**
     * @param multipartFile 웹으로부터 전송된 첨부파일
     */
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    /**
     * @return 첨부파일 File Object
     */
    public File getAttachedFile() {
        if (StringUtils.isNotEmpty(getPath())) {
            //+ File.separator
            return new File(getPath()  + getConvertName());
        } else {
            throw new AttachedFileNotFoundException("There are no files uploaded.");
        }
    }

    /**
     * 첨부파일을 디스크에서 삭제한다.
     * @throws IOException 첨부파일 삭제 실패의 경우
     */
    public void deleteAttachedFile() throws IOException {
        FileUtils.forceDelete(getAttachedFile());
    }

    /**
     * 업로드된 파일을 특정 디렉토리로 이동시킨다.
     * @param dest 목적지 파일
     * @throws IOException 파일을 읽거나, 쓰기 시 에러가 날 경우
     */
    public void transferMultipartFile(File dest) throws IOException {
        if (isMultipartFileEmpty()) {
            throw new IllegalStateException("There are no files uploaded.");
        }
        FileUtils.forceMkdir(dest);
        multipartFile.transferTo(dest);
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 있는지의 여부
     */
    public boolean isMultipartFileNotEmpty() {
        return multipartFile != null && !multipartFile.isEmpty();
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 없는지의 여부
     */
    public boolean isMultipartFileEmpty() {
        return multipartFile == null || multipartFile.isEmpty();
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 있는지 여부
     */
    public boolean isNew() {
        return isMultipartFileNotEmpty() ? true : false;
    }
}