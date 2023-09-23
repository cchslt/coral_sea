package com.fnd.psi.dto.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class WarehouseRelationUserInfoDTO {

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 所属用户id
     */
    private Long belongUserId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;
}
