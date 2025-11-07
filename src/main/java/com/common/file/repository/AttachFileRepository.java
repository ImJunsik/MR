package com.common.file.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.common.file.domain.AttachFile;

/**
 * 첨부파일에 대한 데이터베이스 처리를 담당한다.
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
@Repository
public class AttachFileRepository extends BaseSqlMapRepository {
    /**
     * 첨부파일 코드 값에 해당하는 첨부파일 정보를 반환한다.
     * @param code 첨부파일 코드
     * @return 첨부파일 정보
     */
    public AttachFile getAttachFile(String code) {
        return selectOne("common.file.getAttachFile", code);
    }

    /**
     * 첨부파일 코드 값들에 해당하는 첨부파일 정보를 반환한다.
     * @param codes 첨부파일 코드
     * @return 첨부파일 정보
     */
    public List<AttachFile> getAttachFileList(List<String> codes) {
        return selectList("common.file.getAttachFileList", codes);
    }

    /**
     * 첨부파일 정보를 저장한다.
     * @param attachFile 첨부파일 정보
     * @return 저장된 로우 수
     */
    public int insertAttachFile(AttachFile attachFile) {
        return insert("common.file.insertAttachFile", attachFile);
    }

    /**
     * 첨부파일을 삭제한다.
     * @param code 첨부파일 코드
     * @return 삭제된 로우 수
     */
    public int deleteAttachFile(String code) {
        return deleteAttachFiles(Arrays.asList(code));
    }

    /**
     * 여러건의 첨부파일을 삭제한다.
     * @param codes 첨부파일 코드
     * @return 삭제된 로우 수
     */
    public int deleteAttachFiles(List<String> codes) {
        return delete("common.file.deleteAttachFile", codes);
    }

    public AttachFile getAttachFileDrawMngNo(String drawMngNo) {
    	
    	System.out.println("getAttachFileDrawMngNo === >> ");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("drawMngNo", drawMngNo);
        return selectOne("mr.common.file.getAttachFileDrawMngNo", param);
    }
    
    public AttachFile getAttachFileDrawMngDevNo(String drawMngNo) {
    	
    	System.out.println("getAttachFileDrawMngNo === >> ");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("drawMngNo", drawMngNo);
        return selectOne("mr.common.file.getAttachFileDrawMngDevNo", param);
    }

}