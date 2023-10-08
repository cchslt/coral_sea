package com.fnd.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fnd.psi.dto.BasePageQuery;
import com.fnd.psi.dto.vo.PsiWarehouseRequestVO;
import com.fnd.psi.dto.vo.WarehouseRelationUserInfoDTO;
import com.fnd.psi.model.WarehouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: skipper
 * @Date: 2022-02-10
 * @Desc: 仓库主表 Mapper
 * @See:
 */
public interface WarehouseInfoMapper extends BaseMapper<WarehouseInfo> {


    IPage<WarehouseInfo> selectWarehouseInfoPage(Page page,
                                                 @Param("ew") PsiWarehouseRequestVO basePageQuery,
                                                 @Param("userId") Long userId,
                                                 @Param("countryId") Long countryId);



    /**
     * 根据用户与三级区域 查询符合要求的仓库列表
     *
     * @param areaId
     * @param userId
     * @return
     */
    List<WarehouseInfo> selectWarehouseByAreaAndUserId(@Param("areaId") Long areaId, @Param("userId") Long userId);


    /**
     * 根据仓库id集合查询对应的商家用户
     */
    List<WarehouseRelationUserInfoDTO> selectWarehouseInfoByWarehouseList(@Param("list") List<Long> warehouseId);
}
