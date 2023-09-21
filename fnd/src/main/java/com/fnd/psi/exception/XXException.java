package com.fnd.psi.exception;

import com.fnd.psi.dto.vo.ResultVo;
import lombok.Data;

/**
 * @See: 接收全局异常代码 提供给错误拦截器做识别处理
 */
@Data
public class XXException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;


    public XXException(String code) {
        super();
        this.code = code;
    }

    public XXException(Integer code) {
        super();
        this.code = String.valueOf(code);
    }

    public XXException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public XXException(Integer code, String message) {
        super(message);
        this.code = String.valueOf(code);
        this.message = message;
    }

    public XXException(ResultVo resultVo) {
        super(resultVo.getMsg());
        this.code = String.valueOf(resultVo.getCode());
        this.message = resultVo.getMsg();
    }


}
