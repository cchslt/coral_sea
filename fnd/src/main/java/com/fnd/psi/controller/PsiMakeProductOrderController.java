package com.fnd.psi.controller;

import com.fnd.psi.dto.*;
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
@Api(value = "/makeProduct", description = "制作作品相关", tags = {"PsiMakeProductOrderController"})
@RestController
@RequestMapping(value = "/makeProduct" , produces = {"application/json"})
public class PsiMakeProductOrderController {

    @Autowired
    private PsiTransferringOrderService psiTransferringOrderService;


    /**
     * 新增制作作品信息
     */
    @ApiOperation("制作作品信息 列表")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PageDTO<PsiTransferringOrderDTO>> page(@RequestBody PsiTransferringOrderQuery psiTransferringOrderQuery){
        return psiTransferringOrderService.listPage(psiTransferringOrderQuery);
    }


    /**
     * 新增制作作品信息
     */
    @ApiOperation("制作作品信息 新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> save(@RequestBody @Validated PsiTransferringOrderDTO psiTransferringOrder){
        return psiTransferringOrderService.save(psiTransferringOrder);
    }


    /**
     *
     */
    @ApiOperation("制作作品单-调拨")
    @RequestMapping(value = "/transferring", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> transferring(@RequestBody @Validated PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO){
        return psiTransferringOrderService.transferring(psiTransferringOrderUpdateDTO);
    }


    @ApiOperation("制作作品信息-修改状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiTransferringOrderDTO> updateStatus(@RequestBody @Validated PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO){
        return psiTransferringOrderService.updateStatus(psiTransferringOrderUpdateStatusDTO);
    }
}
