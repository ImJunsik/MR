package com.base.orm;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.base.exception.BaseException;

/**
 * Repository 단에서 예외가 발생했음을 나타내는 exception 클래스이다.
 * 본 exception 외에 업무적으로 repository단에서 필요한 exception 있을 경우 해당 클래스를 상속받아 구현하도록 한다.
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
public class RepositoryException extends BaseException {
    private static final long serialVersionUID = 1L;

    /**
     * 기본 생성자
     */
    public RepositoryException() {
        super();
    }

    /**
     * 지정된 메시지로 새로운 exception을 생성
     * @param message 예외 메시지
     */
    public RepositoryException(String message) {
        this(message, DEFAULT_ERROR_CODE);
    }

    /**
     * 지정된 메시지, 에러코드로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param errorCode 예외 코드
     */
    public RepositoryException(String message, int errorCode) {
        this(message, null, errorCode);
    }

    /**
     * 지정된 메시지와 이전 예외 클래스로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param cause 이전 예외 클래스
     */
    public RepositoryException(String message, Exception cause) {
        this(message, cause, DEFAULT_ERROR_CODE);
    }

    /**
     * 지정된 메시지, 이전 예외 클래스, 에러코드로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public RepositoryException(String message, Exception cause, int errorCode) {
        super(message, cause, errorCode);
    }

    /**
     * Message Resource를 이용하여 예외 메시지를 처리하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     */
    public RepositoryException(MessageSource messageSource, String messageKey) {
        this(messageSource, messageKey, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 에러코드로 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param errorCode 예외 코드
     */
    public RepositoryException(MessageSource messageSource, String messageKey, int errorCode) {
        this(messageSource, messageKey, null, null, errorCode);
    }

    /**
     * Message Resource 및 메시지 파라미터를 이용한 예외 메시지 처리를하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     */
    public RepositoryException(MessageSource messageSource, String messageKey, Object[] messageParameters) {
        this(messageSource, messageKey, messageParameters, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource 및 메시지 파라미터를 이용한 예외 메시지 처리를하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param errorCode 예외 코드
     */
    public RepositoryException(MessageSource messageSource, String messageKey, Object[] messageParameters, int errorCode) {
        this(messageSource, messageKey, messageParameters, null, errorCode);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param cause 이전 예외 클래스
     */
    public RepositoryException(MessageSource messageSource, String messageKey, Exception cause) {
        this(messageSource, messageKey, cause, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public RepositoryException(MessageSource messageSource, String messageKey, Exception cause, int errorCode) {
        this(messageSource, messageKey, null, cause, errorCode);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param cause 이전 예외 클래스
     */
    public RepositoryException(MessageSource messageSource, String messageKey,
            Object[] messageParameters, Exception cause) {
        this(messageSource, messageKey, messageParameters, cause, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource를 이용한 다국어 예외 메시지 처리와 이전 예외 클래스 등으로 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public RepositoryException(MessageSource messageSource, String messageKey,
            Object[] messageParameters, Exception cause, int errorCode) {
        this(messageSource, messageKey, messageParameters, Locale.getDefault(), cause, errorCode);
    }

    /**
     * Message Resource 및 Locale을 이용한 다국어 예외 메시지 처리와 이전 예외 클래스 등으로 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param locale 시스템 로케일
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public RepositoryException(MessageSource messageSource, String messageKey,
            Object[] messageParameters, Locale locale, Exception cause, int errorCode) {
        super(messageSource, messageKey, messageParameters, locale, cause, errorCode);
    }
}