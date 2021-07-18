package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

/**
 * @author chen
 * @create 2021-07-14 23:58
 **/

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition){
        Object bean = null;


        try {
            bean = beanDefinition.getBeanClazz().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化fail");
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
