package com.base.orm.ibatis.type;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>String type의 데이터베이스 컬럼(VARCHAR, CHAR, CLOB)을 access할 때 String type의
 * 데이터를 어떻게 handling 할 것인지를 구현한 클래스인다.</p>
 * <p>프로젝트에서는 Oracle charset이 'WE8ISO8859P1' 으로 기본적으로 한글처리가
 * 안되므로 본 클래스에서는 한글 encoding/decoding을 주 목적으로 사용된다.</p>
 * <p>sqlmap-config.xml에 정의</p>
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
public class StringTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, decoding(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return encoding(rs.getString(columnName));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return encoding(rs.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return encoding(cs.getString(columnIndex));
    }

    /**
     * MS949 로 인코딩한 Text를 반환한다.
     * @param columnValue 인코딩할 Text
     * @return 인코딩된 Text
     * @throws SQLException 데이터베이스 특정 컬럼 값의 인코딩이 실패했을 경우
     */
    private String encoding(String columnValue) throws SQLException {
        try {
            if (columnValue != null) {
                return new String(columnValue.getBytes("8859_1"), "MS949");
            }
        } catch (UnsupportedEncodingException e) {
            throw new SQLException("Unsupported Encoding type.", e);
        }
        return columnValue;
    }

    /**
     * Text를 8859_1 로 디코딩한다.
     * @param parameterValue 디코딩할 Text
     * @return 디코딩된 Text
     * @throws SQLException 디코딩이 실패했을 경우
     */
    private String decoding(String parameterValue) throws SQLException {
        try {
            return new String(parameterValue.getBytes("MS949"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            throw new SQLException("Unsupported Encoding type.", e);
        }
    }
}