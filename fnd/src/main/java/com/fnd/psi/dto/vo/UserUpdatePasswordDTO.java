package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserUpdatePasswordDTO {

    @ApiModelProperty("userId")
    private Long userId;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("确认密码")
    private String confirmPassword;


}
