package com.common.file.exception;

import com.base.exception.BaseException;


/**
 * 특정 첨부파일을 찾지 못 했음을 나타낸다.
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
public class AttachedFileNotFoundException extends BaseException {
    private static final long serialVersionUID = -5616291645051449164L;

    public AttachedFileNotFoundException() {

    }

    public AttachedFileNotFoundException(String message) {
        super(message);
    }

    public AttachedFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}