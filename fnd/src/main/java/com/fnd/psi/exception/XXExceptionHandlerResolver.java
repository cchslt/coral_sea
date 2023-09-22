package com.fnd.psi.exception;

import cn.hutool.core.util.StrUtil;
import com.fnd.psi.dto.vo.ResultVo;
import com.fnd.psi.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Objects;


@Slf4j
@RestControllerAdvice
public class XXExceptionHandlerResolver {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(XXException.class)
    public ResultVo handleSimbaStoreException(XXException e) {
        if(Objects.isNull(e)){
            return ResultVoUtil.error();
        }
        String errorCode = e.getCode();
        String errorMsg = e.getMessage();
        Locale locale = LocaleContextHolder.getLocale();
        if(Objects.isNull(errorCode)){
            return ResultVoUtil.error();
        }
        ResultVo returnResult=new ResultVo();
        returnResult.setCode(Integer.parseInt(errorCode));
        returnResult.setMsg(errorMsg);
        //错误码不为空、错误信息为空 则需要国际化注入错误信息
        if(StrUtil.isNotEmpty(errorCode) &&  StrUtil.isEmpty(errorMsg) &&  messageSource!=null){
            returnResult.setMsg(messageSource.getMessage(errorCode,null,locale));
            //替换带进来的配置信息
        }
        log.error("XXException 异常捕捉 code={},msg={}", errorCode, errorMsg);
        return returnResult;
    }


}