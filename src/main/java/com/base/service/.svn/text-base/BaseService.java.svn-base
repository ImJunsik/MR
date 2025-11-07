package com.base.service;

import java.util.List;

import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mr.step.domain.ChrgrChgHist;
import com.mr.tech.domain.TechInvestVO;
import com.mr.tech.domain.TechReviewVO;

/**
 * Service layer 최상위 클래스로 모든 업무에서 Service 클래스 개발시 해당 클래스를 상속받아 구현하도록한다.
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
public class BaseService {
    /**
     * Service layer에서의 exception 내용 등에 사용할 message resource bundle
     */
    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected XMLConfiguration appConfig;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 개별 트랜잭션 처리를 위해 TransactionStatus 객체를 반환한다.
     * @return 트랜잭션 객체
     */
    protected TransactionStatus getTransactionStatus() {
        return getTransactionStatus(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    /**
     * 트랜잭션의 전파 규칙에 해당하는 TransactionStatus 객체를 반환한다.
     * @param propagation 트랜잭션의 전파 규칙
     * @return 트랜잭션 객체
     */
    protected TransactionStatus getTransactionStatus(int propagation) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(propagation);
        return transactionManager.getTransaction(new DefaultTransactionDefinition(definition));
    }

    /**
     * 현재 트랜잭션을 commit한다.
     * @param transactionStatus 트랜잭션 객체
     */
    protected void commit(TransactionStatus transactionStatus) {
        transactionManager.commit(transactionStatus);
    }

    /**
     * 현재 트랜잭션을 rollback한다.
     * @param transactionStatus 트랜잭션 객체
     */
    protected void rollback(TransactionStatus transactionStatus) {
        transactionManager.rollback(transactionStatus);
    }

	public void insertMrTechInvestBack(TechInvestVO techInvestVO, TechReviewVO techReviewVO) {
		// TODO Auto-generated method stub
		
	}

}