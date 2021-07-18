package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen
 * @create 2021-07-15 00:06
 **/

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanMap = new ConcurrentHashMap<>();



    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = beanMap.get(name);
        if (beanDefinition == null) throw new RuntimeException("No bean named " + name + " is defined");

        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanMap.put(beanName, beanDefinition);
    }
}
