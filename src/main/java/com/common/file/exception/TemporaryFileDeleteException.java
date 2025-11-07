package com.common.file.exception;

import org.springframework.context.MessageSource;

import com.base.scheduling.JobException;

/**
 * 임시파일 삭제에 실패했음을 나타낸다.
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
public class TemporaryFileDeleteException extends JobException {
    private static final long serialVersionUID = -7154441145067290502L;

    public TemporaryFileDeleteException(String message, Exception cause) {
        super(message, cause, DEFAULT_ERROR_CODE);
    }

    public TemporaryFileDeleteException(MessageSource messageSource, String messageKey) {
        super(messageSource, messageKey, DEFAULT_ERROR_CODE);
    }

    public TemporaryFileDeleteException(MessageSource messageSource, String messageKey, Object[] messageParameters) {
        super(messageSource, messageKey, messageParameters, DEFAULT_ERROR_CODE);
    }
}