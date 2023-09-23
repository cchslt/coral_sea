package com.fnd.psi.annotation;

import java.lang.annotation.*;

/**
 * @Date: 2022/2/21/021 20:24
 * @Desc: 标记需要翻译枚举的字段
 * @See:
 */
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransitionEnum {
    /**
     * 指向枚举类
     * @return
     */
    Class enumClass();

    /**
     * 翻译值的key eg: value
     * @return
     */
    String suffix() default "value";
}
