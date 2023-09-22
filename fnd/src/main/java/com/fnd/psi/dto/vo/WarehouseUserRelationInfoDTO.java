package com.fnd.psi.dto.vo;

import lombok.Data;


@Data
public class WarehouseUserRelationInfoDTO {


    /**
     *  仓库id
     *
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     *  psi用户id
     *
     */
    private Long userId;

}
