package com.fnd.psi.annotation.convertor;


import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Skipper
 * @Date: 2022/2/21/021 20:33
 * @Desc: Object类型转换转换
 * @See:
 */
public class SetConvertor implements Convertor{
    @Override
    public Set<?> convert(Object object) throws IllegalAccessException, InvocationTargetException {
        Set<?> objSet = (Set<?>) object;
        objSet = objSet.stream().filter(item -> item != null)
                .map(obj -> EnumConvert.convert(obj))
                .collect(Collectors.toSet());
        return objSet;
    }
}
