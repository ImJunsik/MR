package com.base.servlet.tags.util;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.base.servlet.tags.Pagination;

public class PaginationUtils {
    public static void bindTotalRecordCount(Pagination pagination, Object object, String propertyName) {
        String value = "0";

        try {
            if (object instanceof List) {
                List<?> list = (List<?>)object;
                if (list != null && list.size() > 0) {
                    value = BeanUtils.getProperty(((List<?>)object).get(0), propertyName);
                }
            } else {
                value = BeanUtils.getProperty(object, propertyName);
            }
            pagination.setTotalRecordCount(Integer.parseInt(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}