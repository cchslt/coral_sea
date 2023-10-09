package com.fnd.psi.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-22 16:05
 * @Desc:
 * @See:
 */
@Slf4j
@Component
public class FndSecurityContextFilter extends GenericFilterBean {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String authHeaderValue = null;
            try {
                authHeaderValue = TokenUtil.verify(httpServletRequest);
            } catch (Exception e) {
                log.error("token错误或者已过期, error: ", e);
                resolver.resolveException(httpServletRequest, httpServletResponse, null, new XXException(ErrorCodeConstants.TOKEN_IS_ERROR));
                return;
            }
            if (ObjectUtil.isEmpty(authHeaderValue)) {
                //注意，不一定有token存在， 直接放行
                chain.doFilter(request, response);
                return;
            }


            String tokenInfo = TokenUtil.getTokenInfo(authHeaderValue);
            PsiUserInfoDTO psiUserInfoDTO= JSONUtil.toBean(tokenInfo, PsiUserInfoDTO.class);

            //先构造一个空的上下文，此处暂时不做拦截，只做最基础的安全上下文填充，让后续的权限验证机制再去做拦截
            FndSecurityContext omsSecurityContext = FndSecurityContextUtil.createEmptyContext();
            omsSecurityContext.setPsiUserInfoDTO(psiUserInfoDTO);
            FndSecurityContextUtil.setContext(omsSecurityContext);

            chain.doFilter(request, response);

        } finally {
            FndSecurityContextUtil.clearContext();
        }

    }
}
