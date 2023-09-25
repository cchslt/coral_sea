package com.fnd.psi.configuration;

import cn.hutool.core.collection.CollUtil;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.utils.ResultUtils;
import com.fnd.psi.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2022-05-23 14:48
 * @Desc:
 * @See:
 */
@Slf4j
@RestControllerAdvice
public class ValidatorExceptionHandler {
    @Autowired
    private ResultUtils resultUtils;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {
        List<ObjectError> errors =ex.getBindingResult().getAllErrors();
        if (CollUtil.isNotEmpty(errors)) {
            for (ObjectError error : errors) {
                error.getDefaultMessage();
                return ResultVoUtil.error(error.getDefaultMessage());
            }
        }
        return resultUtils.success();
    }
}
