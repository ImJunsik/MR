package com.common.file.service;

import java.util.List;

import com.common.file.domain.MrAtchFile;

/**
 * 첨부파일에 대한 비지니스(업무 로직) 로직을 표현한다.
 *
 * @author 조상욱
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.08.18 조상욱 최초 작성
 * </pre>
 */
public interface MrAtchFileService {
    /**
     * 첨부파일 코드 값에 해당하는 첨부파일 정보를 반환한다.
     * @param code 첨부파일 코드
     * @return 첨부파일 정보
     */
    MrAtchFile getMrAtchFile(String code);

    /**
     *
     * @param mrReqNo
     * @param mrStepNo
     * @return
     */
    List<MrAtchFile> getMrAtchFileList(int mrReqNo, String mrStepNo);

    /**
     *
     * @param mrReqNo
     * @param mrStepNo
     * @param chrgrClCd
     * @return
     */
    List<MrAtchFile> getMrAtchFileChrgrList(int mrReqNo, String mrStepNo, String chrgrClCd);

    /**
     * 첨부파일 정보를 저장(입력, 수정) 한다.
     * <p>첨부파일 저장 후 저장된 첨부파일 키 값을 담은 첨부파일 정보를 반환한다.<p>
     * @param mrAtchFiles 첨부파일 정보
     * @return 첨부파일 키 값을 담은 첨부파일 정보
     */
    MrAtchFile saveMrAtchFile(MrAtchFile mrAtchFiles);

    /**
     * 여러건의 첨부파일 정보를 저장(입력, 수정) 한다. MR번호,MR스텝
     * <p>첨부파일 저장 후 저장된 첨부파일 키 값을 담은 첨부파일 정보를 반환한다.<p>
     * @param mrAtchFiles 첨부파일 정보
     * @return 첨부파일 키 값을 담은 첨부파일 정보
     */
    List<MrAtchFile> saveMrAtchFiles(List<MrAtchFile> mrAtchFiles);

    /**
     * 여러건의 첨부파일 정보를 저장(입력, 수정) 한다. MR번호,MR스텝,담당자코드
     * <p>첨부파일 저장 후 저장된 첨부파일 키 값을 담은 첨부파일 정보를 반환한다.<p>
     * @param mrAtchFiles 첨부파일 정보
     * @return 첨부파일 키 값을 담은 첨부파일 정보
     */
    List<MrAtchFile> saveMrAtchChrgrFiles(List<MrAtchFile> mrAtchFiles);

    /**
     * 첨부파일을 삭제한다.
     * @param code 첨부파일 코드
     */
    void deleteMrAtchFile(int code);

    /**
     * 여러건의 첨부파일을 삭제한다.
     * @param codes 첨부파일 코드
     */
    void deleteMrAtchFiles(List<Integer> codes);
}