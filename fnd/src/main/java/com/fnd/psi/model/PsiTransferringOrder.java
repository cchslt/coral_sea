package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:42
 * @Desc:
 * @See:
 */
@TableName(value="t_psi_transferring_order")
@Data
public class PsiTransferringOrder implements Serializable {

    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  主键
     *
     */
    private Long id;

    /**
     * 调拨单编码
     */
    private String transferCode;

    /**
     * 转仓的调拨源单
     */
    private Long sourceTransferId;

    /**
     * 转仓的调拨源单
     */
    private String sourceTransferCode;

    /**
     *  调拨源仓库id
     *
     */
    private Long sourceWarehouseId;


//    /**
//     *  调拨目标仓库id
//     *
//     */
//    private Long targetWarehouseId;


    /**
     *  skuid
     *
     */
    private Long productSkuId;


    /**
     *  sku编码
     *
     */
    private String productSkuCode;


    /**
     *  冗余 商品名称
     *
     */
    private String productSkuName;


    /**
     *  商品数量
     *
     */
    private Integer productCount;


    /**
     *  已入库数量
     *
     */
    private Integer receivedStorageCount;


    /**
     *  未入库数量
     *
     */
    private Integer notYetStorageCount;

    /**
     * 转仓数量
     */
    private Integer transportCount;

    /**
     * 转仓入库数量
     */
    private Integer transportStorageCount;

    /**
     *  调拨单状态
     *
     */
    private Integer transferringStatus;


    /**
     *  入库状态，1未入库、2部分入库、3全部入库、0已经关闭
     *
     */
    private Integer relationStorageStatus;


    /**
     *  数据类型：1:调拨， 2: 销售
     *
     */
    private Integer addType;


    /**
     *  所属psi用户id
     *
     */
    private Long belongUserId;


    /**
     *  备注
     *
     */
    private String remarks;


    /**
     *  创建人
     *
     */
    private Long createBy;


    /**
     *  最后修改人
     *
     */
    private Long updateBy;


    /**
     *  是否删除0:未删除，1删除
     *
     */
    private Integer isDeleted;


    /**
     *  创建时间
     *
     */
    private Date gmtCreate;


    /**
     *  更新时间
     *
     */
    private Date gmtModified;


}