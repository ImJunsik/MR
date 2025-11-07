package com.common.file.service;

import java.util.List;

import com.common.file.domain.AttachFile;

/**
 * 첨부파일에 대한 비지니스(업무 로직) 로직을 표현한다.
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
public interface AttachFileService {
    /**
     * 첨부파일 코드 값에 해당하는 첨부파일 정보를 반환한다.
     * @param code 첨부파일 코드
     * @return 첨부파일 정보
     */
    AttachFile getAttachFile(String code);


    AttachFile getAttachFileDrawMngNo(String drawMngNo);
    
    AttachFile getAttachFileDrawMngDevNo(String drawMngNo);

    /**
     * 첨부파일 정보를 저장(입력, 수정) 한다.
     * <p>첨부파일 저장 후 저장된 첨부파일 키 값을 담은 첨부파일 정보를 반환한다.<p>
     * @param attachFile 첨부파일 정보
     * @return 첨부파일 키 값을 담은 첨부파일 정보
     */
    AttachFile saveAttachFile(AttachFile attachFile);

    /**
     * 여러건의 첨부파일 정보를 저장(입력, 수정) 한다.
     * <p>첨부파일 저장 후 저장된 첨부파일 키 값을 담은 첨부파일 정보를 반환한다.<p>
     * @param attachFiles 첨부파일 정보
     * @return 첨부파일 키 값을 담은 첨부파일 정보
     */
    List<AttachFile> saveAttachFiles(List<AttachFile> attachFiles);

    /**
     * 첨부파일을 삭제한다.
     * @param code 첨부파일 코드
     */
    void deleteAttachFile(String code);

    /**
     * 여러건의 첨부파일을 삭제한다.
     * @param codes 첨부파일 코드
     */
    void deleteAttachFiles(List<String> codes);
}