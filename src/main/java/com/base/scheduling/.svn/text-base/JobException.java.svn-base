package com.base.scheduling;

import java.util.Locale;

import org.quartz.JobExecutionException;
import org.springframework.context.MessageSource;

/**
 * 잡 스케줄러가 수행 도중 에러가 났음을 나타낸다.
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
public class JobException extends JobExecutionException {
    public static final int DEFAULT_ERROR_CODE = -999;

    protected String message = null;
    protected String messageKey = null;
    protected Object[] messageParameters = null;
    protected Exception wrappedException = null;
    protected int errorCode;

    private static final long serialVersionUID = 1L;

    /**
     * 기본 생성자
     */
    public JobException() {
        super();
    }

    /**
     * 지정된 메시지로 새로운 exception을 생성
     * @param message 예외 메시지
     */
    public JobException(String message) {
        this(message, DEFAULT_ERROR_CODE);
    }

    /**
     * 지정된 메시지, 에러코드로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param errorCode 예외 코드
     */
    public JobException(String message, int errorCode) {
        this(message, null, errorCode);
    }

    /**
     * 지정된 메시지와 이전 예외 클래스로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param cause 이전 예외 클래스
     */
    public JobException(String message, Exception cause) {
        this(message, cause, DEFAULT_ERROR_CODE);
    }

    /**
     * 지정된 메시지, 이전 예외 클래스, 에러코드로 새로운 exception을 생성
     * @param message 예외 메시지
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public JobException(String message, Exception cause, int errorCode) {
        super(cause);
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * Message Resource를 이용하여 예외 메시지를 처리하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     */
    public JobException(MessageSource messageSource, String messageKey) {
        this(messageSource, messageKey, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 에러코드로 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param errorCode 예외 코드
     */
    public JobException(MessageSource messageSource, String messageKey, int errorCode) {
        this(messageSource, messageKey, null, null, errorCode);
    }

    /**
     * Message Resource 및 메시지 파라미터를 이용한 예외 메시지 처리를하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     */
    public JobException(MessageSource messageSource, String messageKey, Object[] messageParameters) {
        this(messageSource, messageKey, messageParameters, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource 및 메시지 파라미터를 이용한 예외 메시지 처리를하는 새로운 exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param errorCode 예외 코드
     */
    public JobException(MessageSource messageSource, String messageKey, Object[] messageParameters, int errorCode) {
        this(messageSource, messageKey, messageParameters, null, errorCode);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param cause 이전 예외 클래스
     */
    public JobException(MessageSource messageSource, String messageKey, Exception cause) {
        this(messageSource, messageKey, cause, DEFAULT_ERROR_CODE);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param cause 이전 예외 클래스
     * @param errorCode 예외 코드
     */
    public JobException(MessageSource messageSource, String messageKey, Exception cause, int errorCode) {
        this(messageSource, messageKey, null, cause, errorCode);
    }

    /**
     * Message Resource를 이용한 예외 메시지 처리와 이전 예외 클래스로  exception 생성
     * @param messageSource 메시지 소스
     * @param messageKey 메시지 소스내의 키 값
     * @param messageParameters 메시지 파라미터
     * @param cause 이전 예외 클래스
     */
    public JobException(MessageSource messageSource, String messageKey,
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
    public JobException(MessageSource messageSource, String messageKey,
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
    public JobException(MessageSource messageSource, String messageKey,
            Object[] messageParameters, Locale locale, Exception cause, int errorCode) {
        super(cause);
        this.messageKey = messageKey;
        this.messageParameters = messageParameters.clone();
        this.wrappedException = cause;
        this.errorCode = errorCode;
        this.message = messageSource.getMessage(messageKey, messageParameters, locale);
    }

    /**
     * exception 메시지를 반환한다.
     * @return exception 메시지
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * exception 메시지 key를 반환한다.
     * @return exception 메시지 key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * exception 메시지 파라미터를 반환한다.
     * @return exception 메시지 파라미터
     */
    public Object[] getMessageParameters() {
        return messageParameters;
    }

    /**
     * 전파된 exception 객체를 반환한다.
     * @return 전파된 exception 객체
     */
    public Exception getWrappedException() {
        return wrappedException;
    }

    /**
     * 에러코드를 반환한다.
     * @return 에러코드
     */
    @Override
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 에러코드를 설정한다.
     * @param errorCode 에러코드
     */
    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}