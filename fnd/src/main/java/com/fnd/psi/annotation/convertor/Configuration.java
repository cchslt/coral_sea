package com.fnd.psi.annotation.convertor;



import com.fnd.psi.annotation.TransitionEnumLabel;
import com.fnd.psi.exception.XXException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date: 2022/2/21/021 20:31
 * @Desc:
 * @See:
 */
public class Configuration {
    private Map<Class, Convertor> convertorMap;
    private Map<Class, Method> methodMap;

    private static Configuration INSTANCE;

    private Configuration() {
        convertorMap = new HashMap<>();
        methodMap = new HashMap<>();

        convertorMap.put(Object.class, ConvertFactory.getObjectConvertor());
        convertorMap.put(List.class, ConvertFactory.getListConvertor());
        convertorMap.put(Set.class, ConvertFactory.getSetConvertor());
        convertorMap.put(Map.class, ConvertFactory.getMapConvertor());
    }

    public Configuration registerConvert(Class clz, Convertor convertor) {
        if (!convertorMap.containsKey(clz)) {
            convertorMap.put(clz, convertor);
        }
        return this;
    }

    public void registerMethod(Class clz, Method method) {
        if (!methodMap.containsKey(clz)) {
            methodMap.put(clz, method);
        }
    }

    public Convertor getConvertor(Class clz) {
        if (ConvertUtils.isInterfaceImpl(clz)) {
            Class key = ConvertUtils.getInterfaceClass(clz);
            return convertorMap.get(key);
        } else {
            return convertorMap.getOrDefault(clz, new ObjectConvertor());
        }
    }

    public Set<Class> getConvertKeys() {
        return convertorMap.keySet();
    }


    public Method getMethod(Class clz) {
        Method method = methodMap.get(clz);
        if (method == null) {
            List<Method> methodList = Arrays.stream(clz.getDeclaredMethods())
                    .filter(item -> item.getAnnotation(TransitionEnumLabel.class) != null)
                    .collect(Collectors.toList());
            if (methodList.size() == 0) {
                throw new XXException("Could not find ConvertMethod in{}", clz.getName());
            } else if (methodList.size() > 1) {
                throw new XXException("multiple ConvertMethod Found in {}", clz.getName());
            } else {
                method = methodList.get(0);
            }
        }
        if (!methodMap.containsKey(clz)) {
            registerMethod(clz, method);
        }
        return method;
    }

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }
        return INSTANCE;
    }
}
