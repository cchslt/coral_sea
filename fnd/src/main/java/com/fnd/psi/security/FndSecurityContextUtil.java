package com.fnd.psi.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.util.Assert;


public class FndSecurityContextUtil {



	private static final TransmittableThreadLocal<FndSecurityContext> THREAD_LOCAL = new TransmittableThreadLocal<>();


	public static void clearContext() {
		THREAD_LOCAL.remove();
	}

	/**
	 * 获取安全上下文，如果当前不存在，则会构建一个新的OmsSecurityContext
	 *
	 * @return
	 */
	public static FndSecurityContext getContext() {
		FndSecurityContext securityContext = THREAD_LOCAL.get();
		if (securityContext == null) {
			securityContext = createEmptyContext();
			THREAD_LOCAL.set(securityContext);
		}
		return securityContext;
	}

	public static void setContext(FndSecurityContext context) {
		Assert.notNull(context, "Only non-null OmsSecurityContext instances are permitted");
		context.setId(context.getPsiUserInfoDTO().getUser().getId());
		context.setUsername(context.getPsiUserInfoDTO().getUser().getUserName());
		THREAD_LOCAL.set(context);
	}

	public static FndSecurityContext createEmptyContext() {
		return new FndSecurityContext();
	}


	public static String getCurrentUserName() {
		return getContext().getUsername();
	}

	public static boolean isAuthentication() {
		FndSecurityContext context = getContext();
		return ObjectUtil.isNotEmpty(context) && ObjectUtil.isNotNull(context.getId());
	}
}
