package com.fnd.psi.controller;

import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.product.PsiProductSkuDTO;
import com.fnd.psi.dto.vo.PsiProductSkuVO;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.PsiProductSkuService;
import com.fnd.psi.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:42
 * @Desc:
 * @See:
 */
@Api(value = "/sku", description = "商品sku信息表相关接口", tags = {"sku"})
@RestController
@RequestMapping(value = "/sku" , produces = {"application/json"})
public class PsiProductSkuController {

    @Autowired
    private PsiProductSkuService psiProductSkuService;
    @Autowired
    private ResultUtils resultUtils;

    /**
     * 新增商品sku信息表
     */
    @ApiOperation("商品sku信息表 新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<PsiProductSkuDTO> save(@RequestBody @Validated PsiProductSkuDTO psiProductSku){
        return resultUtils.returnSuccess(psiProductSkuService.save(psiProductSku));
    }



    @ApiOperation("商品sku信息表 列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @FndPreAuthorize
    public ResultVo<List<PsiProductSkuDTO>> list(@RequestBody PsiProductSkuVO productSkuVO){
        return resultUtils.returnSuccess(psiProductSkuService.list(productSkuVO));
    }

    @FndPreAuthorize
    @ApiOperation("商品sku信息表 列表")
    @RequestMapping(value = "/listPage", method = RequestMethod.POST)
    public ResultVo<PageDTO<PsiProductSkuDTO>> listPage(@RequestBody PsiProductSkuVO productSkuVO){
        return resultUtils.returnSuccess(psiProductSkuService.listPage(productSkuVO));
    }

}
