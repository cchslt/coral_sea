package com.fnd.psi.annotation.convertor;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Skipper
 * @Date: 2022/2/21/021 20:33
 * @Desc: list类型转换
 * @See:
 */
public class ListConvertor implements Convertor{

    @Override
    public List convert(Object object) throws IllegalAccessException, InvocationTargetException {
        List<?> objList = (List)object;
        objList = objList.stream().filter(item -> item != null)
                .map(obj -> EnumConvert.convert(obj))
                .collect(Collectors.toList());
        return objList;
    }
}
