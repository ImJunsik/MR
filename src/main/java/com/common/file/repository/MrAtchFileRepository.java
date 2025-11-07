package com.common.file.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.common.file.domain.MrAtchFile;

/**
 * 첨부파일에 대한 데이터베이스 처리를 담당한다.
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
@Repository
public class MrAtchFileRepository extends BaseSqlMapRepository {

    /**
     * 첨부파일 코드 값에 해당하는 첨부파일 정보를 반환한다.
     * @param code 첨부파일 코드
     * @return 첨부파일 정보
     */
    public MrAtchFile getMrAtchFile(String code) {
        return selectOne("common.file.getMrAtchFile", code);
    }

    /**
     * 첨부파일 코드 값들에 해당하는 첨부파일 정보를 반환한다.
     * @param mrReqNo(MR번호), mrStepCd(MR단계)에 해당하는 모든 첨부파일 반환
     * @return 첨부파일 정보
     */
    public List<MrAtchFile> getMrAtchFileList(int mrReqNo, String mrStepCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mrReqNo", mrReqNo);
        param.put("mrStepCd", mrStepCd);
        return selectList("common.file.getMrAtchFileList", param);
    }

    /**
     * 첨부파일 코드 값들에 해당하는 첨부파일 정보를 반환한다.
     * @param mrReqNo(MR번호), mrStepCd(MR단계), chrgrClCd(담당자)에 해당하는 모든 첨부파일 반환
     * @return 첨부파일 정보
     */
    public List<MrAtchFile> getMrAtchFileChrgrList(int mrReqNo, String mrStepCd, String chrgrClCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mrReqNo", mrReqNo);
        param.put("mrStepCd", mrStepCd);
        param.put("chrgrClCd", chrgrClCd);
        return selectList("common.file.getMrAtchFileChrgrList", param);
    }

    /**
     * 첨부파일 정보를 저장한다.
     * @param mrAtchFile 첨부파일 정보
     * @return 저장된 로우 수
     */
    public int insertMrAtchFile(MrAtchFile mrAtchFile) {
        return insert("common.file.insertMrAtchFile", mrAtchFile);
    }

    /**
     * 첨부파일 정보를 저장한다.
     * @param mrAtchFile 첨부파일 정보
     * @return 저장된 로우 수
     */
    public int insertMrAtchChrgrFile(MrAtchFile mrAtchFile) {
        return insert("common.file.insertMrAtchChrgrFile", mrAtchFile);
    }

    /**
     * 첨부파일을 삭제한다.
     * @param code 첨부파일 코드
     * @return 삭제된 로우 수
     */
    public int deleteMrAtchFile(int code) {
        return deleteMrAtchFiles(Arrays.asList(code));
    }

    /**
     * 여러건의 첨부파일을 삭제한다.
     * @param codes 첨부파일 코드
     * @return 삭제된 로우 수
     */
    public int deleteMrAtchFiles(List<Integer> codes) {
        return delete("common.file.deleteMrAtchFile", codes);
    }

}