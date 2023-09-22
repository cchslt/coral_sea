package com.fnd.psi.utils;


import com.fnd.psi.constant.CommonConstant;
import com.fnd.psi.dto.vo.ResultVo;

/**
 * ResultVo工具类
 *
 * @author Alex
 * @date 2019-01-15 13:31
 */
public class ResultVoUtil {

    public static ResultVo success(Object data) {
        return new ResultVo<>(CommonConstant.SUCCESS, data, CommonConstant.SUCCESS_MESSAGE);
    }

    public static ResultVo success(Object data, String msg) {
        return new ResultVo<>(CommonConstant.SUCCESS, data, msg);
    }

    public static ResultVo success() {
        return new ResultVo<>(CommonConstant.SUCCESS, CommonConstant.SUCCESS_MESSAGE);
    }

    public static ResultVo error(int code, String errorMsg) {
        return new ResultVo<>(code, new Object(), errorMsg);
    }

    public static ResultVo error(String errorMsg) {
        return new ResultVo<>(CommonConstant.ERROR, errorMsg);
    }


    public static ResultVo error() {
        return new ResultVo<>(CommonConstant.ERROR, CommonConstant.ERROR_MESSAGE);
    }


    public static ResultVo fail() {
        return new ResultVo<>(CommonConstant.FAIL, CommonConstant.FAIL_MESSAGE);
    }

    public static ResultVo fail(String failMsg) {
        return ResultVo.builder().code(CommonConstant.FAIL).msg(failMsg).build();
//        return new ResultVo<>(CommonConstant.FAIL, Boolean.FALSE, failMsg);
    }

    public static ResultVo fail(String failMsg, Object obj) {
        return new ResultVo<>(CommonConstant.FAIL, obj, failMsg);
    }
}
