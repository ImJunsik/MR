package com.mr.detailsearch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.mr.detailsearch.domain.DetailSearchVO;
import com.mr.detailsearch.repository.DetailSearchDAO;
import com.mr.detailsearch.service.DetailSearchService;
import com.mr.main.domain.MrListVO;
import com.mr.mrrq.domain.MrAlterVO;

/**
 * 상세검색 서비스 구현
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * </pre>
 */
@Service
public class DetailSearchServiceImpl extends BaseService implements DetailSearchService{

    @Autowired
    DetailSearchDAO detailSearchDAO;

    @Override
    public List<MrListVO> selectDetailSearch(DetailSearchVO detailSearchVO) {
        return detailSearchDAO.selectDetailSearch(detailSearchVO);
    }
    
    
    @Override
    public List<MrAlterVO> alterSearch(MrAlterVO mrAlterVO) {
        return detailSearchDAO.alterSearch(mrAlterVO);
    }
    

    @Override
    public List<MrListVO> selectDetailSearchTotal(DetailSearchVO detailSearchVO) {
        return detailSearchDAO.selectDetailSearchTotal(detailSearchVO);
    }

    @Override
    public List<MrListVO> selectDetailSearchCode(DetailSearchVO detailSearchVO) {
        return detailSearchDAO.selectDetailSearchCode(detailSearchVO);
    }

}
