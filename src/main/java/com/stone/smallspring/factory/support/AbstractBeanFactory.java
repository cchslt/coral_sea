package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.BeanFactory;
import com.stone.smallspring.factory.config.BeanDefinition;

/**
 * @author chen
 * @create 2021-07-14 23:54
 **/

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) {
       return doGetBean(name, null);
    }


    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }


    protected <T> T doGetBean(final String name, final Object... args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T)bean;
        }


        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) ;


}
