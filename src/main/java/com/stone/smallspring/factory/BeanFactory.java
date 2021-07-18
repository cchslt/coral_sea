package com.stone.smallspring.factory;


/**
 * @author chen
 * @create 2021-07-14 23:27
 **/

public interface BeanFactory {


    Object getBean(String name) ;


    Object getBean(String name, Object... args);

}
