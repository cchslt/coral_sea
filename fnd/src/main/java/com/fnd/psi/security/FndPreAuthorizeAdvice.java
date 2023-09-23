package com.fnd.psi.security;

import cn.hutool.core.util.StrUtil;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.exception.XXException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class FndPreAuthorizeAdvice {


	@Around("@annotation(fndPreAuthorize)")
	public Object invoke(ProceedingJoinPoint proceedingJoinPoint, FndPreAuthorize fndPreAuthorize) throws Throwable {
		boolean authentication = FndSecurityContextUtil.isAuthentication();
		if (!authentication) {
			//未登录 非法请求 直接拦截
			throw new XXException(ErrorCodeConstants.USER_NOT_LOGIN);
		}
		String value = fndPreAuthorize.value();
		if (StrUtil.isBlank(value)) {
			//不需要鉴权
			return proceedingJoinPoint.proceed();
		} else {
			//非法访问
			log.info("【OMS安全拦截】username:{}访问权限：{}被拦截", FndSecurityContextUtil.getContext().getUsername(), value);
			throw new XXException(ErrorCodeConstants.USER_NOT_LOGIN);
		}


	}
}

