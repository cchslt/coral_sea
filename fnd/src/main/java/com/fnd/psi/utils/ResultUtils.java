package com.fnd.psi.utils;

import cn.hutool.core.util.StrUtil;
import com.fnd.psi.constant.CommonConstant;
import com.fnd.psi.dto.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
@Slf4j
public class ResultUtils {

    @Autowired
    private MessageSource messageSource;


    /**
     * 接收字符串类型的code
     * @param code
     * @return
     */
    public ResultVo returnFailByCode(String code) {
        ResultVo returnResult = new ResultVo();
        returnResult.setCode(parseStrCodeToInt(code));
        if (messageSource != null) {
            returnResult.setMsg(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
        }
        return returnResult;
    }

    /**
     * 接收字符串类型的code
     * @param code
     * @return
     */
    public ResultVo returnFailByCodeWithData(String code,Object date) {
        ResultVo returnResult = new ResultVo();
        returnResult.setCode(parseStrCodeToInt(code));
        returnResult.setData(date);
        if (messageSource != null) {
            returnResult.setMsg(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
        }
        return returnResult;
    }

    /**
     * 接收数值类型的code
     * @param code
     * @return
     */
    public ResultVo returnFailByCode(Integer code) {
        ResultVo returnResult = new ResultVo();
        returnResult.setCode(code);
        if (messageSource != null) {
            returnResult.setMsg(messageSource.getMessage(String.valueOf(code), null, LocaleContextHolder.getLocale()));
        }
        return returnResult;
    }

    /**
     * 接收错误码与多个需要替换
     * @param code
     * @param objects
     * @return
     */
    public ResultVo returnFailCodeAndFormatMsg(String code, Object... objects) {
        ResultVo returnResult = new ResultVo();
        returnResult.setCode(Integer.parseInt(code));
        if (messageSource != null) {
            Locale locale = LocaleContextHolder.getLocale();
            String errorMsg = messageSource.getMessage(code, null, locale);
            if (StrUtil.isNotBlank(errorMsg)) {
                errorMsg = String.format(locale, errorMsg, objects);
            }
            returnResult.setMsg(errorMsg);
        }
        return returnResult;
    }

    /**
     * 转换有可能超长的字符串错误码
     *
     * @param code
     * @return
     */
    public static Integer parseStrCodeToInt(String code) {
        Integer errorCode = -1;
        try {
            errorCode = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            log.error("转换错误");
            //错误大于10位数 则只取前9位数
            if (StrUtil.isNotBlank(code) && code.length() >= 10) {
                code = code.substring(0, 8);
                errorCode = Integer.valueOf(code);
            }
        } catch (Exception e) {
            //TODO 异常处理
        }
        return errorCode;
    }

    public static ResultVo success(Object data) {
        return new ResultVo<>(CommonConstant.SUCCESS, data, CommonConstant.SUCCESS_MESSAGE);
    }

    public ResultVo returnSuccess(Object data) {
        return new ResultVo<>(CommonConstant.SUCCESS, data, CommonConstant.SUCCESS_MESSAGE);
    }

    public static ResultVo success(Object data, String msg) {
        return new ResultVo<>(CommonConstant.SUCCESS, data, msg);
    }

    public ResultVo success() {
        return new ResultVo();
    }

    /**
     * @return
     * @description: 返回翻译后的内容
     * @author: alex xiao
     * @date: 2021/7/30/030 14:16
     */
    public String getMessage(String code, Object... objects) {
        if (messageSource != null) {
            Locale locale = LocaleContextHolder.getLocale();
            String errorMsg = messageSource.getMessage(code, null, locale);
            if (StrUtil.isNotBlank(errorMsg)) {
                errorMsg = String.format(locale, errorMsg, objects);
            }
            return errorMsg;
        }
        return code;
    }


}
