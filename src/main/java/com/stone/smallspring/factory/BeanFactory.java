package com.stone.smallspring.factory;

import com.stone.smallspring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen
 * @create 2021-07-14 23:27
 **/

public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }


    //bean注册
    public void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
