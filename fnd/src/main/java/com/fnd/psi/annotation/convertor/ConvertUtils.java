package com.fnd.psi.annotation.convertor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2022/2/21/021 20:30
 * @Desc: 转换工具类
 * @See:
 */
public class ConvertUtils {

    public static boolean isInterfaceImpl(Class clz) {
        return Configuration.getInstance().getConvertKeys().stream()
                .filter(item -> item != Object.class)
                .filter(item -> item.isAssignableFrom(clz))
                .count() > 0;
    }

    public static Class getInterfaceClass(Class clz) {
        return Configuration.getInstance().getConvertKeys().stream()
                .filter(item -> item != Object.class)
                .filter(item -> item.isAssignableFrom(clz))
                .findFirst()
                .get();
    }

    public static List<Field> getAllFields(Class clz) {
        List<Field> fieldList = new ArrayList<>();
        while (clz != null) {
            fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
            clz = clz.getSuperclass();
        }
        return fieldList;
    }
}
