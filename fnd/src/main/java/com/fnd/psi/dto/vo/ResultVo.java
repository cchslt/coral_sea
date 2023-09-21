package com.fnd.psi.dto.vo;


import com.fnd.psi.constant.CommonConstant;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code = CommonConstant.SUCCESS;

    @Getter
    @Setter
    private String msg = CommonConstant.SUCCESS_MESSAGE;


    @Getter
    @Setter
    private T data;

    public ResultVo() {
        super();
    }

    public ResultVo(T data) {
        super();
        this.data = data;
    }


    public ResultVo(int code, T data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Boolean isSuccess() {
        return this.code == CommonConstant.SUCCESS;
    }

    public Boolean isNotSuccess() {
        return this.code != CommonConstant.SUCCESS;
    }

    public ResultVo(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = CommonConstant.FAIL;
    }

    public ResultVo(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
