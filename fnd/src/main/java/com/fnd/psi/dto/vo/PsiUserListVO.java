package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class PsiUserListVO {

    /**
     *  主键
     *
     */
    @ApiModelProperty("id")
    private Long id;


    @ApiModelProperty("账号等级 0 是最高等级(主账号)")
    private Integer level;

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
     *  用户类型（0是超级管理 1 商家管理员）
     *
     */
    @ApiModelProperty("用户类型（0是超级管理 1 商家管理员）")
    private Long userType;


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
     *  是否冻结
     *
     */
    @ApiModelProperty("是否冻结（0正常,1冻结）")
    private Integer isFrozen;


    /**
     *  冻结userid
     *
     */
    @ApiModelProperty("冻结人ID")
    private Long frozenUserId;

    /**
     *  所属用户
     */
    @ApiModelProperty("归属那个用户 最大账号 = 0")
    private Long belongUserId;
    /**
     *  国家id
     */
    @ApiModelProperty("国家id")
    private Long countryId;


    /**
     *  国家code
     *
     */
    @ApiModelProperty("国家code")
    private String countryCode;


    /**
     *  创建时间
     *
     */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;


    /**
     *  更新时间
     *
     */
    @ApiModelProperty("更新时间")
    private Date gmtModified;

    @ApiModelProperty("角色集合")
    private List<PsiUserRoleVO> roles;

    @ApiModelProperty("来源类型 0, 默认(psi创建) 1,EGATEE 2,其他")
    private Integer sourceType;

    @ApiModelProperty("来源关联Id")
    private String sourceId;

    @ApiModelProperty("仓库Id")
    private List<Long> warehouseIds;

    @ApiModelProperty("仓库信息")
    private List<WarehouseUserRelationInfoDTO> warehouseInfos;


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
