package com.base.servlet.json.map;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * 웹에서 요청한 데이터를 Domain Object의 String 변수에 assign할 때
 * null value를 "" 와 같이 whitespace 로 assign된다.
 * String의 null 과 "" 값은 엄연히 다른 값이므로 해당 클래스에서는 JsonDeserializer를 상속받아
 * JSON deerialize 시에 ""(whitesapce) 값을 null value로 변경한다.
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
public class WhiteSpaceRemovalDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException,
    JsonProcessingException {
        String text = parser.getText();

        if (StringUtils.isEmpty(text) || (!StringUtils.isEmpty(text) && text.equals("null"))) {
            return null;
        }
        return text;
    }
}