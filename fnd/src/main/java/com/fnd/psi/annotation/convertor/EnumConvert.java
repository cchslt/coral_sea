package com.fnd.psi.annotation.convertor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: Skipper
 * @Date: 2022/2/21/021 20:35
 * @Desc: 统一转换入口
 * @See:
 */
@Slf4j
public class EnumConvert {
    public static Object convert(Object obj) {
        Object ret = obj;
        Convertor convertor = Configuration.getInstance().getConvertor(obj.getClass());
        try {
            ret = convertor.convert(obj);
        } catch (IllegalAccessException e) {
           log.error("EnumConvert->convert异常,{}，存在不存在的枚举值",e.getMessage());
        }catch (InvocationTargetException exception){
            log.error("EnumConvert->convert异常,{}",exception.getMessage());
        }
        return ret;
    }
}
