package com.fnd.psi.annotation;


import cn.hutool.json.JSONUtil;
import com.fnd.psi.annotation.convertor.EnumConvert;
import com.fnd.psi.constant.GlobalErrorCodeConstants;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.exception.XXException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * @Date: 2022/2/21/021 21:25
 * @Desc: 转换逻辑切面实现
 * @See:
 */
//@Aspect
@Slf4j
//@Component
public class TransitionEnumAdvice {


    @Pointcut("@annotation(com.simba.store.common.general.annotation.TransitionEnumMethod)")
    private void TransitionEnum() {
        log.info("==============");
    }

    @Around("TransitionEnum()")
    public ResultVo invoke(ProceedingJoinPoint joinPoint){
        log.info("========TransitionEnumFirst========");
        Object proceedObject = null;
        try {
            proceedObject = joinPoint.proceed();
        }catch (Throwable throwable){
            log.info("TransitionEnumAdvice Error:{}",throwable.getMessage());
            throw new XXException(GlobalErrorCodeConstants.SYSTEM_EXCEPTION);
        }
        ResultVo resultVo =  new ResultVo();
        //处理返回列表处理
        if(proceedObject instanceof  ResultVo){
            ResultVo proceedObjectResultVo = (ResultVo)proceedObject;
            Object  proceedObjectResultVoData =  proceedObjectResultVo.getData();
            if(proceedObjectResultVoData instanceof  List){
                List  proceedObjectResultVoDataList = (List) proceedObjectResultVoData;
                resultVo =  new ResultVo(EnumConvert.convert(proceedObjectResultVoDataList));
            }else {
                //如不为list 则为正常的返回实体 直接解析即可
                resultVo =  new ResultVo(EnumConvert.convert(proceedObjectResultVoData));
            }
        }
        log.info("TransitionEnumFirst resultVo：{}", JSONUtil.toJsonStr(resultVo));
        return resultVo;
    }



}
