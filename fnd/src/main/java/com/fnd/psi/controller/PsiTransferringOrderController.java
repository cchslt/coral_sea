package com.fnd.psi.controller;

import com.fnd.psi.dto.PsiTransferringOrderDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.PsiTransferringOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:48
 * @Desc:
 * @See:
 */
@Api(value = "/psiTransferringOrder", description = "调拨单信息相关接口", tags = {"PsiTransferringOrderController"})
@RestController
@RequestMapping(value = "/psiTransferringOrder" , produces = {"application/json"})
public class PsiTransferringOrderController {

    @Autowired
    private PsiTransferringOrderService psiTransferringOrderService;


    /**
     * 新增调拨单信息
     */
    @ApiOperation("调拨单信息 新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> save(@RequestBody @Validated PsiTransferringOrderDTO psiTransferringOrder){
        return psiTransferringOrderService.save(psiTransferringOrder);
    }


    /**
     * 新增调拨单信息
     */
    @ApiOperation("调拨单信息-调拨")
    @RequestMapping(value = "/transferring", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> transferring(@RequestBody @Validated PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO){
        return psiTransferringOrderService.transferring(psiTransferringOrderUpdateDTO);
    }


    @ApiOperation("调拨单信息-修改状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> updateStatus(@RequestBody @Validated PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO){
        return psiTransferringOrderService.updateStatus(psiTransferringOrderUpdateStatusDTO);
    }
}
