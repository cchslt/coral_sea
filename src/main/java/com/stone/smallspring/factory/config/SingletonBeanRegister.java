package com.stone.smallspring.factory.config;

/**
 * @author chen
 * @create 2021-07-14 23:47
 **/

public interface SingletonBeanRegister {

    Object getSingleton(String beanName);

}
