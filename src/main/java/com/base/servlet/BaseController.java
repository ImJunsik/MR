package com.base.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Controller layer 최상위 클래스로 모든 업무에서 Controller 클래스 개발시 해당 클래스를 상속받아 구현하도록한다.
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
public class BaseController {
    /**
     * Controller layer에서의 exception 내용 등에 사용할 message resource bundle
     */
    @Autowired
    protected MessageSource messageSource;

}