package com.fnd.psi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Skipper
 * @Date: 2022/2/21/021 20:26
 * @Desc: 标记枚举中根据key获取到value函数
 * @See:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransitionEnumLabel {
}
