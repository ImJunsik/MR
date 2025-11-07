package com.common.file.collection.util;

import org.apache.commons.collections.Predicate;

import com.common.file.domain.AttachFile;

/**
 * 여러건의(e.g. List<AttachFile>) 첨부파일 처리 후 (저장, 삭제)
 * List<AttachFile> 안에 실제 유효한 첨부파일 객체를 쉽게 식별할 수 있도록 도와주는 클래스이다.
 *
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 * 
 * @see com.examples.service.impl.EmployeeServiceImpl#updateEmployee(com.examples.domain.Employee)
 */
public class AttachFileAvailablePredicate implements Predicate {
    @Override
    public boolean evaluate(Object object) {
        return ((AttachFile)object).getCode() != null;
    }
}