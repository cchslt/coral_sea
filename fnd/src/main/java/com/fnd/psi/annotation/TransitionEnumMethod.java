package com.fnd.psi.annotation;

import java.lang.annotation.*;

/**
 * @Desc: 标记需要翻译返回参数的函数 通常是controller
 * @See:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransitionEnumMethod {
}
