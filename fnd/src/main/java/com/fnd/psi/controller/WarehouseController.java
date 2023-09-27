package com.fnd.psi.controller;

import com.fnd.psi.dto.BasePageQuery;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.BaseDictionariesVO;
import com.fnd.psi.dto.vo.WarehouseInfoVO;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.WarehouseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date: 2022/1/24/024 20:50
 * @Desc: 仓库相关接口
 * @See:
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/warehouse")
@Api(value = "warehouse", tags = "仓库模块")
public class WarehouseController {

    private WarehouseInfoService warehouseInfoService;

    @ApiOperation(value = "仓库管理页面select page", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/selectPage")
    public ResultVo<PageDTO<WarehouseInfoVO>> selectPage(@RequestBody BasePageQuery basePageQuery) {
        return warehouseInfoService.selectPage(basePageQuery);
    }

    @ApiOperation(value = "获取仓库名称字典", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/getWarehouseNameDictionaries")
    public ResultVo<List<BaseDictionariesVO>> getWarehouseNameDictionaries(@RequestParam(value = "status", required = false) Integer status) {
        return warehouseInfoService.getWarehouseNameDictionaries(status);
    }

    @ApiOperation(value = "获取仓库名称字典", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/getOutWarehouseNameDictionaries")
    public ResultVo<List<BaseDictionariesVO>> getOutWarehouseNameDictionaries(@RequestParam(value = "status", required = false) Integer status) {
        return warehouseInfoService.getOutWarehouseNameDictionaries(status);
    }

    @ApiOperation(value = "获取调入仓库名称字典", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/getInWarehouseNameDictionaries")
    public ResultVo<List<BaseDictionariesVO>> getInWarehouseNameDictionaries(@RequestParam(value = "status", required = false) Integer status) {
        return warehouseInfoService.getInWarehouseNameDictionaries(status);
    }

    @ApiOperation(value = "新增仓库信息", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/addWarehouse")
    public ResultVo<WarehouseInfoVO> addWarehouse(@RequestBody WarehouseInfoVO warehouseInfoVO) {
        return warehouseInfoService.addWarehouse(warehouseInfoVO);
    }


    @ApiOperation(value = "修改仓库信息", httpMethod = "POST")
    @FndPreAuthorize
    @PostMapping(value = "/updateWarehouse")
    public ResultVo<WarehouseInfoVO> updateWarehouse(@RequestBody WarehouseInfoVO warehouseInfoVO) {
        return warehouseInfoService.updateWarehouse(warehouseInfoVO);
    }

//    @ApiOperation(value = "仓库调整优先级-上升", httpMethod = "GET")
//    @FndPreAuthorize
//    @GetMapping(value = "/upPriority/{warehouseId}")
    public ResultVo upPriority(@PathVariable(value = "warehouseId") Long warehouseId) {
        return warehouseInfoService.upPriority(warehouseId);
    }

//    @ApiOperation(value = "仓库调整优先级-下沉", httpMethod = "GET")
//    @FndPreAuthorize
//    @GetMapping(value = "/downPriority/{warehouseId}")
    public ResultVo downPriority(@PathVariable(value = "warehouseId") Long warehouseId) {
        return warehouseInfoService.downPriority(warehouseId);
    }

//    @ApiOperation(value = "仓库按照优先级查询", httpMethod = "POST")
//    @FndPreAuthorize
//    @PostMapping(value = "/selectPriority")
    public ResultVo selectPriority(@RequestBody BasePageQuery basePageQuery) {
        return warehouseInfoService.selectPriority(basePageQuery);
    }


}
