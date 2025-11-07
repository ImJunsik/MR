package com.mr.detailsearch.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.detailsearch.domain.DetailSearchVO;
import com.mr.main.domain.MrListVO;
import com.mr.mrrq.domain.MrAlterVO;
/**
 * 상세검색 DB 처리
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.25 박성룡 최초 작성
 * </pre>
 */
@Repository
public class DetailSearchDAO extends BaseSqlMapRepository {

    /**
     * 상세검색 DB 처리
     * @param detailSearchVO
     * @return MR List
     */
    public List<MrListVO> selectDetailSearch(DetailSearchVO detailSearchVO){
        return selectList("mr.detail.selectDetailSearch", detailSearchVO);
    }
    // yoo 2023.02.16 관리자 페이지 MR 변경 검색
    public List<MrAlterVO> alterSearch(MrAlterVO mrAlterVO){
        return selectList("mr.detail.alterSearch", mrAlterVO);
    }

	public List<MrListVO> selectDetailSearchTotal(DetailSearchVO detailSearchVO) {
        return selectList("mr.detail.selectDetailSearchTotal", detailSearchVO);
	}
	
	public List<MrListVO> selectDetailSearchCode(DetailSearchVO detailSearchVO) {
        return selectList("mr.detail.selectDetailSearchCode", detailSearchVO);
	}
}
