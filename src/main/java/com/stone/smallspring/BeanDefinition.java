package com.stone.smallspring;

/**
 * @author chen
 * @create 2021-07-14 23:25
 **/

public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
