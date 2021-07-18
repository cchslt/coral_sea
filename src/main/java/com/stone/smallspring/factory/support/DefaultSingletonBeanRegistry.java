package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.SingletonBeanRegister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen
 * @create 2021-07-14 23:48
 **/

public class DefaultSingletonBeanRegistry implements SingletonBeanRegister {

    private Map<String, Object> objectMap = new ConcurrentHashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return objectMap.get(beanName);
    }


    protected void addSingleton(String beanName, Object singleton) {
        objectMap.put(beanName, singleton);
    }


}
