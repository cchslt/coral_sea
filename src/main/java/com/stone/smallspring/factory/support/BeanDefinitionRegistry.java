package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

/**
 * @author chen
 * @create 2021-07-15 00:05
 **/

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
