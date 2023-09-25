package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 17:27
 * @Desc:
 * @See:
 */
@TableName(value="t_psi_storage_order")
@Data
public class PsiStorageOrder implements Serializable {

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
     *  入库单单号
     *
     */
    private String storageOrderCode;


    /**
     *  来源id
     *
     */
    private Long sourceId;


    /**
     *  来源单号
     *
     */
    private String sourceCode;


    /**
     *  来源业务单据时间
     *
     */
    private Date sourceBusinessTime;


    /**
     *  所在仓库id
     *
     */
    private Long warehouseId;


    /**
     *  商品数量
     *
     */
    private Integer productCount;


    /**
     *  入库数量
     *
     */
    private Integer receivedCount;


    /**
     *  入库状态，1未入库、2部分入库、3全部入库、0已经关闭
     *
     */
    private Integer storageStatus;


    /**
     *  所属psi用户id
     *
     */
    private Long belongUserId;


    /**
     *  单据备注
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