package com.test.smallspring;

import com.stone.smallspring.factory.config.BeanDefinition;
import com.stone.smallspring.UserService;
import com.stone.smallspring.factory.BeanFactory;
import com.stone.smallspring.factory.support.DefaultListableBeanFactory;

/**
 * @author chen
 * @create 2021-07-14 23:33
 **/

public class SpringTest {

    public static void main(String[] args) {
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        BeanFactory factory = new BeanFactory();
//        factory.registerBean("userService", beanDefinition);
//
//
//        UserService us = (UserService)factory.getBean("userService");
//        us.userInfo();


        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        factory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)factory.getBean("userService");


        UserService userService1 = (UserService)factory.getBean("userService");

    }
}
