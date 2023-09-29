package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.BasePageQuery;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.BaseDictionariesVO;
import com.fnd.psi.dto.vo.WarehouseInfoVO;
import com.fnd.psi.dto.vo.WarehouseRelationUserInfoDTO;
import com.fnd.psi.model.WarehouseInfo;

import java.util.List;

/**
 * @Author: skipper
 * @Date:  2022-02-10
 * @Desc:  仓库主表 Service
 * @See:
 */
public interface WarehouseInfoService extends IService<WarehouseInfo>{


    /**
     * 获取仓库名称字典
     * @return
     */
    ResultVo<List<BaseDictionariesVO>> getWarehouseNameDictionaries(Integer status);

    /**
     * 仓库管理列表查询
     * @param basePageQuery
     * @return
     */
    ResultVo<PageDTO<WarehouseInfoVO>> selectPage(BasePageQuery basePageQuery);

    /**
     * 新增仓库
     * @param warehouseInfoVO
     * @return
     */
    ResultVo<WarehouseInfoVO> addWarehouse(WarehouseInfoVO warehouseInfoVO);

    /**
     * 修改仓库接口
     * @param warehouseInfoVO
     * @return
     */
    ResultVo<WarehouseInfoVO> updateWarehouse(WarehouseInfoVO warehouseInfoVO);

    /**
     * 调整仓库优先级-上升
     * @param warehouseId
     * @return
     */
    ResultVo upPriority(Long warehouseId);

    /**
     * 仓库调整优先级-下沉
     * @param warehouseId
     * @return
     */
    ResultVo downPriority(Long warehouseId);

    /**
     * 仓库按照优先级查询
     * @param basePageQuery
     * @return
     */
    ResultVo selectPriority(BasePageQuery basePageQuery);

    /**
     * 根据id查询仓库信息
     * @param id
     * @return
     */
    WarehouseInfo selectWarehouseInfoById(Long id);

    /**
     * 根据 仓库id获取到仓库名称
     * @param id
     * @return
     */
    String getWarehouseNameById(Long id);


    /**
     * 拿默认仓库
     */
    WarehouseInfo getDefaultWarehouse(Long belongUserId);

    /**
     * 拿默认仓库
     */
    List<Long> getWarehouseIdList(Long belongUserId);

    /**
     * 根据所属用户获取仓库列表
     * @param belongUserId
     * @return
     */
    List<WarehouseInfo> getWarehouseInfoByBelongUserList(Long belongUserId);

    /**
     * 根据国家ID查询仓库信息
     * @param countryId
     * @return
     */
    List<WarehouseInfo> warehouseInfoByCountryId(Long countryId);

    /**
     * 根据仓库Id判断PSI用户是否是R
     * @param wareHouseId
     * @return
     */
    Boolean checkIsRShopByWareHouseId(Long wareHouseId);


    /**
     * 根据仓库id集合查询对应的商家用户
     * @param collect
     * @return
     */
    List<WarehouseRelationUserInfoDTO> getWarehouseInfoByWarehouseList(List<Long> collect);

    /**
     * 查询调出仓库集合
     * @param status
     * @return
     */
    ResultVo<List<BaseDictionariesVO>> getOutWarehouseNameDictionaries(Integer status);

    /**
     * 查询调入仓库集合
     * @param status
     * @return
     */
    ResultVo<List<BaseDictionariesVO>> getInWarehouseNameDictionaries(Integer status);

    /**
     * 删除仓库
     * @param id
     * @return
     */
    ResultVo delete(Long id);
}
