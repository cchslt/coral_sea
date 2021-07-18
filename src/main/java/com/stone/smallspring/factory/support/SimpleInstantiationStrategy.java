package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author chen
 * @create 2021-07-18 17:36
 **/

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * JDK 实例化
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     * @return
     */

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName,
                              Constructor ctor, Object... args) {
        Class clazz = beanDefinition.getBeanClazz();

        try {
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException("实例化失败[" + clazz.getName() + "]", e);
        }

    }
}
