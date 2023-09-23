package com.fnd.psi.annotation.convertor;

import java.lang.reflect.InvocationTargetException;

/**
 * @Date: 2022/2/21/021 20:33
 * @Desc:
 * @See:
 */
public interface Convertor {
    /**
     * 转换枚举翻译
     * @param object
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Object convert(Object object) throws IllegalAccessException, InvocationTargetException;
}
