package com.fnd.psi.annotation.convertor;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.fnd.psi.annotation.TransitionEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date: 2022/2/21/021 20:28
 * @Desc: Object类型转换
 * @See:
 */
@Slf4j
public class ObjectConvertor implements Convertor {

    @Override
    public Object convert(Object object) throws IllegalAccessException, InvocationTargetException {
        Map<String, Object> result = null;
        try {
            if (object.getClass() == String.class || object.getClass() == Integer.class  || object.getClass() == Long.class
            || object.getClass() == Double.class || object.getClass() == Float.class || object.getClass() == BigDecimal.class
            || object.getClass() == Boolean.class) {
                return object;
            }
            result = JSONUtil.parseObj(JSONUtil.toJsonStr(object));
        } catch (Exception e) {
            log.error("ObjectConvertor->convert JSON解析异常:{},异常参数：{}", e.getMessage(), JSONUtil.toJsonStr(object));
            return object;
        }
        List<Field> fieldList = ConvertUtils.getAllFields(object.getClass());
        List<Field> fieldListTmp = new ArrayList<>();

        for (Field field : fieldList) {
            field.setAccessible(true);
            if (ObjectUtil.isNotNull(field) && ObjectUtil.isNotNull(field.get(object))) {
                fieldListTmp.add(field);
            }
        }
        for (Field field : fieldListTmp) {
            field.setAccessible(true);
            if (field.getAnnotation(TransitionEnum.class) != null) {
                TransitionEnum bindEnum = field.getAnnotation(TransitionEnum.class);
                Class enumClass = bindEnum.enumClass();
                Method convertMethod = Configuration.getInstance().getMethod(enumClass);
                Object value = field.get(object);
                Object label = convertMethod.invoke(null, value);
                result.put(field.getName(), label);
            }
        }
        fieldListTmp = fieldListTmp.stream().filter(field -> field.getAnnotation(TransitionEnum.class) == null).collect(Collectors.toList());
        for (Field field : fieldListTmp) {
            Convertor convertor = Configuration.getInstance().getConvertor(field.getType());
            result.put(field.getName(), convertor.convert(field.get(object)));
        }
        return result;
    }
}
