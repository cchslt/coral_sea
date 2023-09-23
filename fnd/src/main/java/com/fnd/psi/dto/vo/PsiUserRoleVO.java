package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class PsiUserRoleVO extends PsiRoleVO implements Serializable {

    @ApiModelProperty("userId")
    private Long userId;

}
