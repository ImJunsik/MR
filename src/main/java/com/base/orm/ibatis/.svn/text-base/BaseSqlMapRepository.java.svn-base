package com.base.orm.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * SqlSessionDaoSupport을 상속하여 좀 더 간단하고 축약된 메소드 이름으로 목적 쿼리를 실행할 수 있도록
 * 도와주는 추상클래스로서 모든 Repository 클래스는 본 클래스를 상속받아 구현하도록 한다.
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
public class BaseSqlMapRepository extends SqlSessionDaoSupport {
    /**
     * insert 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @return insert 로우 수
     */
    public int insert(String statement) {
        return getSqlSession().insert(statement);
    }

    /**
     * insert 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter insert 쿼리 파라미터
     * @return insert 로우 수
     */
    public int insert(String statement, Object parameter) {
        return getSqlSession().insert(statement, parameter);
    }

    /**
     * update 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @return update 로우 수
     */
    public int update(String statement) {
        return getSqlSession().update(statement);
    }

    /**
     * update 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter update 쿼리 파라미터
     * @return update 로우 수
     */
    public int update(String statement, Object parameter) {
        return getSqlSession().update(statement, parameter);
    }

    /**
     * delete 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @return delete 로우 수
     */
    public int delete(String statement) {
        return getSqlSession().delete(statement);
    }

    /**
     * delete 쿼리를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter delete 파라미터
     * @return delete 로우 수
     */
    public int delete(String statement, Object parameter) {
        return getSqlSession().delete(statement, parameter);
    }

    /**
     * 단건 select를 실행한다.
     * @param statement sqlmap statement id
     * @return select 매핑된 오브젝트
     */
    public <T> T selectOne(String statement) {
        return getSqlSession().selectOne(statement);
    }

    /**
     * 단건 select를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter select 파라미터
     * @return 매핑된 오브젝트
     */
    public <T> T selectOne(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }

    /**
     * 단건 select를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter select 파라미터
     * @return int
     */
    public int selectInt(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }
    /**
     * 멀티로우 select를 실행한다.
     * @param statement sqlmap statement id
     * @return 매핑된 오브젝트
     */
    public <E> List<E> selectList(String statement) {
        return getSqlSession().selectList(statement);
    }

    /**
     * 멀티로우 select를 실행한다.
     * @param statement sqlmap statement id
     * @param parameter select 파라미터
     * @return 매핑된 오브젝트
     */
    public <E> List<E> selectList(String statement, Object parameter) {
        return getSqlSession().selectList(statement, parameter);
    }
}