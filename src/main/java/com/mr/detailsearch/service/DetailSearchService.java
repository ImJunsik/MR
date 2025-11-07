package com.mr.detailsearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.detailsearch.domain.DetailSearchVO;
import com.mr.main.domain.MrListVO;
import com.mr.mrrq.domain.MrAlterVO;
/**
 * 상세검색 서비스
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
@Service
public interface DetailSearchService{

    /**
     * 상세검색 서비스
     * @param detailSearchVO
     * @return MR List
     */
    public List<MrListVO> selectDetailSearch(DetailSearchVO detailSearchVO);
    public List<MrAlterVO> alterSearch(MrAlterVO mrAlterVO);
	public List<MrListVO> selectDetailSearchTotal(DetailSearchVO inDetailSearchVO);
	public List<MrListVO> selectDetailSearchCode(DetailSearchVO inDetailSearchVO);

}
