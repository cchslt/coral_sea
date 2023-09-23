package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class UserDTO {


    /**
     *  主键
     *
     */
    @ApiModelProperty("主键")
    private Long id;


    /**
     *  用户名(登录名称)
     *
     */
    @ApiModelProperty("用户名(登录名称)")
    private String userName;


    /**
     *  昵称
     *
     */
    @ApiModelProperty("昵称")
    private String nickName;


    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;


    /**
     *  邮箱
     *
     */
    @ApiModelProperty("邮箱")
    private String email;


    /**
     *  手机号
     *
     */
    @ApiModelProperty("手机号")
    private String phone;


    /**
     * 是否可用 (0 不可用 1 可用)
     *
     */
    @ApiModelProperty("是否可用 (0 不可用 1 可用)")
    private Integer isFrozen;


    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID集合")
    private List<Long> roles;


    /**
     * 仓库Id
     */
    @ApiModelProperty("仓库Id集合")
    private List<Long> warehouseIds;

    /**
     *  调出仓库Id集合
     */
    @ApiModelProperty("调出仓库Id集合")
    private List<Long> outWarehouseIds;


    /**
     *  调入仓库Id集合
     */
    @ApiModelProperty("调入仓库Id集合")
    private List<Long> inWarehouseIds;


}
