package com.base.servlet.json.map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * 웹에서 요청되는 데이터를 특정 객체에 Deserialize할 때 사용될 Deserializer를 선택적으로 등록하는 클래스인다.
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
 * @see com.base.servlet.bind.support.CustomWebBindingInitializer#initBinder(org.springframework.web.bind.WebDataBinder, org.springframework.web.context.request.WebRequest)
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        super.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        initDeserializer();
    }

    private void initDeserializer() {
        SimpleModule module = new SimpleModule("StingJsonDeserializer", new Version(1, 0, 0, null));
        module.addDeserializer(String.class, new WhiteSpaceRemovalDeserializer());
        super.registerModule(module);
    }
}