package com.fnd.psi.dto.user;

import com.fnd.psi.dto.vo.PermissionDTO;
import com.fnd.psi.dto.vo.PsiUserRoleVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PsiUserInfoDTO implements Serializable {

    /**
     * (主)用户基本信息
     */
    @ApiModelProperty("用户基本信息")
    private PsiUserDTO user;

    /**
     * 权限标识集合
     */
    @ApiModelProperty("权限标识集合")
    private List<PermissionDTO> menuData;

    
    @ApiModelProperty("按钮权限标识集合")
    private List<String> permissions;
    /**
     * 角色集合
     */
    @ApiModelProperty("角色集合")
    private List<PsiUserRoleVO> roles;

}
