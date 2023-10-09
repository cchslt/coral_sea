package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:45
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderUpdateStatusDTO extends BaseDTO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(message = "id不能为空")
    private Long id;


    @ApiModelProperty("调拨单状态列表")
    @NotEmpty(message = "调拨单状态列表不能为空")
    @Valid
    private List<PsiTransferringOrderUpdateStatusDetailDTO> transferringStatusList;

    /**
     *  最后修改人
     */
    private Long updateBy;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
