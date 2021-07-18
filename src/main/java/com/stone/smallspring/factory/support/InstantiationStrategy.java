package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author chen
 * @create 2021-07-18 17:34
 **/

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args);
}
