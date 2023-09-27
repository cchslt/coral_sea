package com.fnd.psi.controller;

import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiInventoryVo;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.PsiInventoryService;
import com.fnd.psi.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



//@RestController
//@RequestMapping("/inventory")
//@Api(value = "inventory", tags = "库存相关")
//@AllArgsConstructor
public class InventoryController {

    private PsiInventoryService psiInventoryService;
    private ResultUtils resultUtils;


    @FndPreAuthorize
    @ApiOperation("获取商品的库存")
    @RequestMapping(value = "/findSkuInventory", method = RequestMethod.POST)
    public ResultVo updatePermission(@RequestBody PsiInventoryVo permissionVO) {
        return resultUtils.returnSuccess(psiInventoryService.permissionVO(permissionVO));
    }



}

