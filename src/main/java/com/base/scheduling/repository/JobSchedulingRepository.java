package com.base.scheduling.repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.base.scheduling.JobAttributes;

/**
 * 잡 스케줄러의 실행, 실행상태 등의 정보를 모니터링할 수 있도록 DB에 인서트를 한다.
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
public class JobSchedulingRepository extends BaseSqlMapRepository {
    /**
     * 잡 스케줄러의 실행 시작 시간 등을 저장한다.
     * 
     * @param jobAttributes 잡 스케줄러 정보
     * @return 인서트된 갯 수
     * @throws Exception
     */
    public int insertJobAttributes(JobAttributes jobAttributes) throws Exception {
        return insert("scheduling.insertJobAttributes", jobAttributes);
    }

    /**
     * 잡 시스케줄러의 실행 완료 상태 및 에러 내용을 업데이트 한다.
     * 
     * @param jobAttributes jobAttributes 잡 스케줄러 정보
     * @return 업데이트된 갯 수
     * @throws Exception
     */
    public int updateJobAttributes(JobAttributes jobAttributes) throws Exception {
        return insert("scheduling.updateJobAttributes", jobAttributes);
    }
}