package com.fnd.psi.annotation.convertor;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Date: 2022/2/21/021 20:33
 * @Desc: Map类型转换
 * @See:
 */
public class MapConvertor implements Convertor{
    @Override
    public Map convert(Object object) throws IllegalAccessException, InvocationTargetException {
        Map objMap = (Map)object;
        Iterator<?> it = objMap.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = ((Map) object).get(key);
            if (value != null) {
                objMap.put(key, EnumConvert.convert(value));
            }
        }
        return objMap;
    }
}
