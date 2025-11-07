package com.common.file.collection.util;

import org.apache.commons.collections.Transformer;

import com.common.file.domain.AttachFile;

/**
 * 여러건의(e.g. List<AttachFile>) 첨부파일 객체에 AttachFile.setPath(String path) 값을
 * 쉽게 입력할 수 있도록 도와주는 클래스이다.
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
 * @see com.examples.domain.Employee#getAttachFiles()
 */
public class AttachFilePathTransformer implements Transformer {
    private String path;

    public AttachFilePathTransformer(String path) {
        this.path = path;
    }

    @Override
    public Object transform(Object input) {
        ((AttachFile)input).setPath(path);
        return input;
    }
}