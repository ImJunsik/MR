package com.base.scheduling.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.base.scheduling.JobState;

/**
 * JobState에 대한 편집기. Text 값에 해당하는 JobState로 변환한다.
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
public class JobStatePropertyEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        return getValue() != null ? ((JobState)getValue()).getValue() : null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(JobState.textValueOf(text));
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

    @Override
    public Object getValue() {
        return super.getValue();
    }
}