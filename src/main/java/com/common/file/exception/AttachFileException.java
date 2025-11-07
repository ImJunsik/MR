package com.common.file.exception;

import org.springframework.context.MessageSource;

import com.base.service.ServiceException;

/**
 * 첨부파일 처리 중(쓰기, 읽기 등) 에러가 났음을 나타낸다.
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
public class AttachFileException extends ServiceException {
    private static final long serialVersionUID = -5671544050257560157L;

    public AttachFileException(MessageSource messageSource, String messageKey) {
        super(messageSource, messageKey);
    }

    public AttachFileException(MessageSource messageSource, String messageKey, Throwable cause) {
        super(messageSource, messageKey, cause);
    }
}