package com.stone.smallspring.factory.support;

import com.stone.smallspring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author chen
 * @create 2021-07-14 23:58
 **/

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args){
        Object bean = null;


        try {
            bean = beanDefinition.getBeanClazz().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化fail");
        }

        addSingleton(beanName, bean);
        return bean;
    }


    //使用Cglib实例化
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClazz();
        Constructor<?>[] declaredCtor = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredCtor) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructor = ctor;
                break;
            }
        }

        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);

    }

}
