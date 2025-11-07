package com.base.servlet.bind.support;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 웹에서 요청되는 데이터를 특정 객체에 바인딩할 때 사용될 PropertyEditor를 선택적으로 등록하는 클래스이다.
 * <p>e.g. Whitespace("") 로 요청되는 데이터는 null로 bindding</p>
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
public class CustomWebBindingInitializer extends ConfigurableWebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        super.initBinder(binder, request);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}