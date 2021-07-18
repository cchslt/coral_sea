package com.stone.smallspring.factory.config;

/**
 * @author chen
 * @create 2021-07-14 23:25
 **/

public class BeanDefinition {

    private Class beanClazz;

    public BeanDefinition(Class beanClazz) {
        this.beanClazz = beanClazz;
    }

    public Class getBeanClazz() {
        return beanClazz;
    }

    public void setBeanClazz(Class beanClazz) {
        this.beanClazz = beanClazz;
    }
}
