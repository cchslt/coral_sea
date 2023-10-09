package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fnd.psi.dto.BaseDomain;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-10-09 13:59
 * @Desc:
 * @See:
 */
@TableName(value="t_psi_transferring_order_status")
@Data
public class PsiTransferringOrderStatus extends BaseDomain {


    /**
     *  归属id
     */
    private Long transferringOrderId;
    /**
     *  状态
     */
    private Integer transferringStatus;
    /**
     *  数量
     */
    private Integer productCount;
    /**
     *  创建人
     */
    private Long createBy;
    /**
     *  最后修改人
     */
    private Long updateBy;

}
