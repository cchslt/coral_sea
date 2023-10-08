package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.annotation.convertor.EnumConvert;
import com.fnd.psi.constant.*;
import com.fnd.psi.dto.BasePageQuery;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.dto.vo.*;
import com.fnd.psi.mapper.WarehouseInfoMapper;
import com.fnd.psi.mapper.WarehouseShippingRegionRelationMapper;
import com.fnd.psi.model.*;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.*;
import com.fnd.psi.utils.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service("warehouseInfoService")
@AllArgsConstructor
public class WarehouseInfoServiceImpl extends ServiceImpl<WarehouseInfoMapper, WarehouseInfo> implements WarehouseInfoService {

    private ResultUtils resultUtils;
    private WarehouseInfoMapper warehouseInfoMapper;
    private WarehouseShippingRegionRelationService warehouseShippingRegionRelationService;
    private WarehouseShippingRegionRelationMapper warehouseShippingRegionRelationMapper;
    private WarehouseUserRelationService warehouseUserRelationService;
    private PsiOutWarehouseUserRelationService psiOutWarehouseUserRelationService;
    private PsiInWarehouseUserRelationService psiInWarehouseUserRelationService;
    private UserService userService;
    private PsiUserIdentityService psiUserIdentityService;


    @Override
    public ResultVo<List<BaseDictionariesVO>> getWarehouseNameDictionaries(Integer status) {
        List<BaseDictionariesVO> baseDictionariesVOS = new ArrayList<>();
        final PsiUserDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        if (UserTypeEnum.PLATFORM_MANAGEMENT.getCode().equals(currentUser.getUserType())){
            final List<WarehouseInfo> warehouseInfoList = list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getIsDeleted, false).eq(WarehouseInfo::getCountryId, currentUser.getCountryId()));
            warehouseInfoList.forEach(x ->{
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(x.getId());
                baseDictionariesVO.setName(x.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);

            });
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }

        //查询条件
        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);

        //只查询有权限的仓库的下拉框
        final List<WarehouseUserRelation> warehouseIdByUserIds = warehouseUserRelationService.getWarehouseIdByUserIds(Sets.newHashSet(currentUser.getId()));
        if (CollectionUtil.isEmpty(warehouseIdByUserIds)){
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }
        //暂时就不连表了
        queryWrapper.in(WarehouseInfo::getId, warehouseIdByUserIds.stream()
                .map(WarehouseUserRelation::getWarehouseId).collect(Collectors.toSet()));
        queryWrapper.eq(WarehouseInfo::getBelongUserId, currentUser.getBelongUserId());
        if (Objects.nonNull(status)&&status>0){
            queryWrapper.eq(WarehouseInfo::getWarehouseStatus, WarehouseStatusEnum.ENABLE.getEnumValue());
        }
        queryWrapper.orderByAsc(WarehouseInfo::getInventoryPriority);
        //查询供应商数据并赋值给vo
        List<WarehouseInfo> warehouseInfoList = warehouseInfoMapper.selectList(queryWrapper);
        if (CollectionUtil.isNotEmpty(warehouseInfoList)) {
            warehouseInfoList.forEach(e -> {
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(e.getId());
                baseDictionariesVO.setName(e.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);
            });
        }
        return resultUtils.returnSuccess(baseDictionariesVOS);
    }

    @Override
    public ResultVo<PageDTO<WarehouseInfoVO>> selectPage(PsiWarehouseRequestVO basePageQuery) {
        PageDTO<WarehouseInfoVO> warehouseInfoVOPageDTO = new PageDTO<>();
        if (ObjectUtil.isNotNull(basePageQuery)) {
            final PsiUserDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
            Page page = PSIBaseUtils.buildPageByQuery(basePageQuery);
            //查询供应商数据并赋值给vo
            IPage<WarehouseInfo> pageData = warehouseInfoMapper.selectWarehouseInfoPage(page,basePageQuery, currentUser.getId(),null); //只查询当前用户有权限的仓库
            if (ObjectUtil.isNotNull(pageData)&&CollUtil.isNotEmpty(pageData.getRecords())) {
                final List<WarehouseInfo> warehouseInfos = pageData.getRecords();
                final Set<Long> warehouseInfoId = warehouseInfos.stream().map(WarehouseInfo::getId).collect(Collectors.toSet());

                final List<WarehouseShippingRegionRelation> warehouseShippingRegionRelations = warehouseShippingRegionRelationService.list(new LambdaQueryWrapper<WarehouseShippingRegionRelation>()
                        .in(WarehouseShippingRegionRelation::getWarehouseId, warehouseInfoId).eq(WarehouseShippingRegionRelation::getIsDeleted, 0));
                warehouseInfoVOPageDTO = CopyBeanUtils.convert(pageData, PageDTO.class);
                //CopyBeanUtils.copyList(pageData.getRecords(), WarehouseInfoVO.class);
                final List<WarehouseInfoVO> warehouseInfoVOS = Lists.newArrayList();
                final List<WarehouseInfo> records = pageData.getRecords();
                records.forEach(x ->{
                    final WarehouseInfoVO warehouseInfoVO = CopyBeanUtils.convert(x, WarehouseInfoVO.class);
                    warehouseInfoVO.setWarehouseContacter(x.getWarehouseContact());
                    warehouseInfoVOS.add(warehouseInfoVO);
                });
                warehouseInfoVOPageDTO.setRecords(warehouseInfoVOS);
            }
        }

        return resultUtils.returnSuccess(warehouseInfoVOPageDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public ResultVo<WarehouseInfoVO> addWarehouse(WarehouseInfoVO warehouseInfoVO) {
        if (ObjectUtil.isNull(warehouseInfoVO)) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        if (StrUtil.isEmpty(warehouseInfoVO.getWarehouseName())) {
            return resultUtils.returnFailByCode(ErrorCodeConstants.WAREHOUSE_NAME_CAN_NOT_BE_NULL);
        }
        WarehouseInfo warehouseInfo = CopyBeanUtils.convert(warehouseInfoVO, WarehouseInfo.class);
        warehouseInfo.setWarehouseCode(PSICodeUtils.getWarehouseCode());
        warehouseInfo.setIsVirtual(Boolean.TRUE);
        warehouseInfo.setIsDeleted(CommonConstant.IS_DELETED_FALSE);
        warehouseInfo.setWarehouseContact(warehouseInfoVO.getWarehouseContacter());
        PsiUserDTO psiUser = null;
        if (Objects.nonNull(warehouseInfoVO.getBelongUserId())) {
            psiUser = new PsiUserDTO();
            psiUser.setId(warehouseInfoVO.getBelongUserId());
            psiUser.setBelongUserId(warehouseInfoVO.getBelongUserId());
            psiUser.setCountryId(warehouseInfoVO.getCountryId());
        } else {
            psiUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
//            if (CollectionUtil.isEmpty(warehouseInfoVO.getShippingRegionIds())) {
//                return resultUtils.returnFailByCode(ErrorCodeConstants.WAREHOUSE_SHOPPING_REGION_CAN_NOT_BE_EMPTY);
//            }
        }
        if (ObjectUtil.isNotNull(psiUser)) {
            warehouseInfo.setCountryId(psiUser.getCountryId());
            warehouseInfo.setBelongUserId(psiUser.getBelongUserId());
            warehouseInfo.setCreateBy(psiUser.getId());
            warehouseInfo.setUpdateBy(psiUser.getId());
        }

        //维护区域绑定关系
        warehouseInfo.addNecessaryProperties();
        warehouseInfo.setInventoryPriority(Objects.nonNull(warehouseInfoVO.getInventoryPriority())
                ?warehouseInfoVO.getInventoryPriority():Integer.MAX_VALUE);
        boolean result = save(warehouseInfo);
        if (!result) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.SYSTEM_EXCEPTION);
        }
        /**
         * 建立用户与仓库的绑定关系
         */
        warehouseUserRelationService.addUserWarehouseRelation(psiUser.getBelongUserId(), Arrays.asList(warehouseInfo.getId()));
        psiOutWarehouseUserRelationService.addPsiOutWarehouseUserRelation(psiUser.getBelongUserId(), Arrays.asList(warehouseInfo.getId()));
        psiInWarehouseUserRelationService.addPsiInWarehouseUserRelation(psiUser.getBelongUserId(), Arrays.asList(warehouseInfo.getId()));

        return resultUtils.returnSuccess(CopyBeanUtils.convert(warehouseInfo, WarehouseInfoVO.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<WarehouseInfoVO> updateWarehouse(WarehouseInfoVO warehouseInfoVO) {
        Boolean syncPsiInventoryToEs = true;
        if (ObjectUtil.isNull(warehouseInfoVO)) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        if (ObjectUtil.isNull(warehouseInfoVO.getId())) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }


        WarehouseInfo warehouseInfo = warehouseInfoMapper.selectById(warehouseInfoVO.getId());
        warehouseInfo.setWarehouseName(warehouseInfoVO.getWarehouseName());
        warehouseInfo.setWarehouseContact(warehouseInfoVO.getWarehouseContacter());
        warehouseInfo.setWarehousePhone(warehouseInfoVO.getWarehousePhone());
        warehouseInfo.setWarehouseAddress(warehouseInfoVO.getWarehouseAddress());
        warehouseInfo.setWarehouseRemark(warehouseInfoVO.getWarehouseRemark());

        log.info("updateWarehouse"+ JSONUtil.toJsonStr(warehouseInfoVO));
        int result = warehouseInfoMapper.updateById(warehouseInfo);
        if (result < 1) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.SYSTEM_EXCEPTION);
        }

        return resultUtils.returnSuccess(CopyBeanUtils.convert(warehouseInfo, WarehouseInfoVO.class));
    }


    @Override
    public List<WarehouseRelationUserInfoDTO> getWarehouseInfoByWarehouseList(List<Long> warehouseList) {
        if (CollUtil.isEmpty(warehouseList)) {
            return null;
        }
        List<WarehouseRelationUserInfoDTO> warehouseRelationUserInfoDTOS = warehouseInfoMapper.selectWarehouseInfoByWarehouseList(warehouseList);
        return warehouseRelationUserInfoDTOS;
    }

    @Override
    public ResultVo<List<BaseDictionariesVO>> getOutWarehouseNameDictionaries(Integer status) {
        List<BaseDictionariesVO> baseDictionariesVOS = new ArrayList<>();
        final PsiUserDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        if (UserTypeEnum.PLATFORM_MANAGEMENT.getCode().equals(currentUser.getUserType())){
            final List<WarehouseInfo> warehouseInfoList = list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getIsDeleted, false).eq(WarehouseInfo::getCountryId, currentUser.getCountryId()));
            warehouseInfoList.forEach(x ->{
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(x.getId());
                baseDictionariesVO.setName(x.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);

            });
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }
        if (IntegerUtils.isEquality(0, currentUser.getLevel())) {
            return getWarehouseNameDictionaries(WarehouseStatusEnum.ENABLE.getEnumValue());
        }

        //查询条件
        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);

        //只查询有权限的仓库的下拉框
        final List<PsiOutWarehouseUserRelation> warehouseIdByUserIds = psiOutWarehouseUserRelationService.getWarehouseIdByUserId(currentUser.getId());
        if (CollectionUtil.isEmpty(warehouseIdByUserIds)){
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }
        //暂时就不连表了
        queryWrapper.in(WarehouseInfo::getId, warehouseIdByUserIds.stream()
                .map(PsiOutWarehouseUserRelation::getWarehouseId).collect(Collectors.toSet()));

        queryWrapper.eq(WarehouseInfo::getBelongUserId, currentUser.getBelongUserId());
        if (Objects.nonNull(status)&&status>0){
            queryWrapper.eq(WarehouseInfo::getWarehouseStatus, WarehouseStatusEnum.ENABLE.getEnumValue());
        }
        queryWrapper.orderByAsc(WarehouseInfo::getInventoryPriority);
        //查询供应商数据并赋值给vo
        List<WarehouseInfo> warehouseInfoList = warehouseInfoMapper.selectList(queryWrapper);
        if (CollectionUtil.isNotEmpty(warehouseInfoList)) {
            warehouseInfoList.forEach(e -> {
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(e.getId());
                baseDictionariesVO.setName(e.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);
            });
        }
        return resultUtils.returnSuccess(baseDictionariesVOS);
    }

    @Override
    public ResultVo<List<BaseDictionariesVO>> getInWarehouseNameDictionaries(Integer status) {
        List<BaseDictionariesVO> baseDictionariesVOS = new ArrayList<>();
        final PsiUserDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        if (UserTypeEnum.PLATFORM_MANAGEMENT.getCode().equals(currentUser.getUserType())){
            final List<WarehouseInfo> warehouseInfoList = list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getIsDeleted, false).eq(WarehouseInfo::getCountryId, currentUser.getCountryId()));
            warehouseInfoList.forEach(x ->{
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(x.getId());
                baseDictionariesVO.setName(x.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);

            });
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }
        if (IntegerUtils.isEquality(0, currentUser.getLevel())) {
            return getWarehouseNameDictionaries(WarehouseStatusEnum.ENABLE.getEnumValue());
        }

        //只查询有权限的仓库的下拉框
        final List<PsiInWarehouseUserRelation> warehouseIdByUserIds = psiInWarehouseUserRelationService.getWarehouseIdByUserId(currentUser.getId());
        if (CollectionUtil.isEmpty(warehouseIdByUserIds)){
            return resultUtils.returnSuccess(baseDictionariesVOS);
        }

        //查询条件
        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);
        queryWrapper.in(WarehouseInfo::getId, warehouseIdByUserIds.stream().map(PsiInWarehouseUserRelation::getWarehouseId).collect(Collectors.toSet()));
        queryWrapper.eq(WarehouseInfo::getBelongUserId, currentUser.getBelongUserId());
        queryWrapper.eq(Objects.nonNull(status)&&status>0, WarehouseInfo::getWarehouseStatus, WarehouseStatusEnum.ENABLE.getEnumValue());
        queryWrapper.orderByAsc(WarehouseInfo::getInventoryPriority);

        List<WarehouseInfo> warehouseInfoList = warehouseInfoMapper.selectList(queryWrapper);
        if (CollectionUtil.isNotEmpty(warehouseInfoList)) {
            warehouseInfoList.forEach(e -> {
                BaseDictionariesVO baseDictionariesVO = new BaseDictionariesVO();
                baseDictionariesVO.setId(e.getId());
                baseDictionariesVO.setName(e.getWarehouseName());
                baseDictionariesVOS.add(baseDictionariesVO);
            });
        }
        return resultUtils.returnSuccess(baseDictionariesVOS);
    }

    @Override
    public ResultVo delete(Long id) {
        final PsiUserDTO psiUserDTO = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        lambdaUpdate().set(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_TRUE)
                .set(WarehouseInfo::getUpdateBy, psiUserDTO.getId())
                .set(WarehouseInfo::getGmtModified, new Date())
                .eq(WarehouseInfo::getId, id)
                .eq(WarehouseInfo::getIsDeleted,CommonConstant.IS_DELETED_FALSE)
                .update();

        return resultUtils.success();
    }

    @Override
    public ResultVo upPriority(Long warehouseId) {
        if (ObjectUtil.isNull(warehouseId)) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }

        final PsiUserDTO psiUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();

        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getBelongUserId, psiUser.getBelongUserId());
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);
        //暂时就不连表了
        final List<WarehouseUserRelation> warehouseIdByUserIds = warehouseUserRelationService.getWarehouseIdByUserIds(Sets.newHashSet(psiUser.getId()));
        if (CollectionUtil.isEmpty(warehouseIdByUserIds)){
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        queryWrapper.in(WarehouseInfo::getId, warehouseIdByUserIds.stream()
                .map(WarehouseUserRelation::getWarehouseId).collect(Collectors.toSet()));

        queryWrapper.orderByAsc(WarehouseInfo::getInventoryPriority);

        List<WarehouseInfo> warehouseInfoList = warehouseInfoMapper.selectList(queryWrapper);

        int location = 0;
        for (WarehouseInfo info : warehouseInfoList) {
            if (info.getId().longValue() == warehouseId.longValue()) {
                break;
            }
            ++location;
        }
        if (location != 0 && location < warehouseInfoList.size()) {
            WarehouseInfo warehouseInfo = warehouseInfoList.get(location - 1);
            WarehouseInfo swapWarahouse = warehouseInfoList.get(location);
            warehouseInfoList.set(location, warehouseInfo);
            warehouseInfoList.set(location - 1, swapWarahouse);

            Integer init = 0;
            for (WarehouseInfo info : warehouseInfoList) {
                info.setInventoryPriority(init++);
            }
            boolean result = updateBatchById(warehouseInfoList);
            if (!result) {
                return resultUtils.returnFailByCode(GlobalErrorCodeConstants.SYSTEM_EXCEPTION);
            }
        }

        return resultUtils.success();
    }

    @Override
    public ResultVo downPriority(Long warehouseId) {
        if (ObjectUtil.isNull(warehouseId)) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }

        final PsiUserDTO psiUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();

        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getBelongUserId, psiUser.getBelongUserId());
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);
        //暂时就不连表了
        final List<WarehouseUserRelation> warehouseIdByUserIds = warehouseUserRelationService.getWarehouseIdByUserIds(Sets.newHashSet(psiUser.getId()));
        if (CollectionUtil.isEmpty(warehouseIdByUserIds)){
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        queryWrapper.in(WarehouseInfo::getId, warehouseIdByUserIds.stream()
                .map(WarehouseUserRelation::getWarehouseId).collect(Collectors.toSet()));

        queryWrapper.orderByAsc(WarehouseInfo::getInventoryPriority);

        List<WarehouseInfo> warehouseInfoList = warehouseInfoMapper.selectList(queryWrapper);

        int location = 0;
        for (WarehouseInfo info : warehouseInfoList) {
            if (info.getId().longValue() == warehouseId.longValue()) {
                break;
            }
            ++location;
        }
        if (location != warehouseInfoList.size() - 1) {
            WarehouseInfo warehouseInfo = warehouseInfoList.get(location);
            WarehouseInfo swapWarahouse = warehouseInfoList.get(location + 1);
            warehouseInfoList.set(location + 1, warehouseInfo);
            warehouseInfoList.set(location, swapWarahouse);

            Integer init = 0;
            for (WarehouseInfo info : warehouseInfoList) {
                info.setInventoryPriority(init++);
            }
            boolean result = updateBatchById(warehouseInfoList);
            if (!result) {
                return resultUtils.returnFailByCode(GlobalErrorCodeConstants.SYSTEM_EXCEPTION);
            }
        }
        return resultUtils.success();
    }

    @Override
    public ResultVo selectPriority(BasePageQuery basePageQuery) {
        PageDTO<WarehousePriorityVO> warehousePriorityVOPageDTO = new PageDTO<>();

        if (ObjectUtil.isNull(basePageQuery)) {
            return resultUtils.returnFailByCode(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        if (ObjectUtil.isNull(basePageQuery.getPageNo()) || ObjectUtil.isNull(basePageQuery.getPageSize())) {
            return resultUtils.returnFailByCode(ErrorCodeConstants.PAGE_PARAMETER_CAN_NOT_BE_NULL);
        }

        IPage<WarehouseInfo> page = new Page(basePageQuery.getPageNo(), basePageQuery.getPageSize());
        LambdaQueryWrapper<WarehouseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WarehouseInfo::getIsDeleted, CommonConstant.IS_DELETED_FALSE);
        queryWrapper.eq(WarehouseInfo::getBelongUserId, FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser().getBelongUserId());
        queryWrapper.orderByDesc(WarehouseInfo::getInventoryPriority)
                    .orderByAsc(WarehouseInfo::getGmtCreate);

        IPage<WarehouseInfo> pageData = warehouseInfoMapper.selectPage(page, queryWrapper);
        if (ObjectUtil.isNotNull(pageData)) {
            warehousePriorityVOPageDTO = CopyBeanUtils.convert(pageData, PageDTO.class);
            warehousePriorityVOPageDTO.setRecords(CopyBeanUtils.copyList(pageData.getRecords(), WarehousePriorityVO.class));
        }

        return resultUtils.returnSuccess(warehousePriorityVOPageDTO);
    }

    @Override
    public WarehouseInfo selectWarehouseInfoById(Long id) {
        WarehouseInfo warehouseInfo = null;
        if (ObjectUtil.isNotNull(id)) {
            warehouseInfo = baseMapper.selectById(id);
        }
        warehouseInfo = Optional.ofNullable(warehouseInfo).orElse(new WarehouseInfo());
        return warehouseInfo;
    }



    @Override
    public String getWarehouseNameById(Long id) {
        String  warehouseName = null;
        if(ObjectUtil.isNotNull(id)){
           if(StrUtil.isEmpty(warehouseName)){
               WarehouseInfo warehouseInfo = baseMapper.selectById(id);;
               if(ObjectUtil.isNotNull(warehouseInfo)){
                   warehouseName = warehouseInfo.getWarehouseName();
               }
           }
        }
        return warehouseName;
    }

    @Override
    public WarehouseInfo getDefaultWarehouse(Long belongUserId) {

        return getOne(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getBelongUserId,belongUserId)
                .eq(WarehouseInfo::getWarehouseType, WarehouseTypeEnum.EGATEE_AUTO_CREATE_WAREHOUSE.getCode())
                .eq(WarehouseInfo::getIsDeleted,false));
        
    }

    @Override
    public List<Long> getWarehouseIdList(Long belongUserId) {

        return list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getBelongUserId,belongUserId)
                //.eq(WarehouseInfo::getWarehouseType, WarehouseTypeEnum.EGATEE_AUTO_CREATE_WAREHOUSE.getCode())
                .eq(WarehouseInfo::getIsDeleted,false)).stream().map(WarehouseInfo::getId).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseInfo> getWarehouseInfoByBelongUserList(Long belongUserId) {
        return list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getBelongUserId,belongUserId)
                .eq(WarehouseInfo::getIsDeleted,false));
    }

    @Override
    public List<WarehouseInfo> warehouseInfoByCountryId(Long countryId) {
        return list(new LambdaQueryWrapper<WarehouseInfo>().eq(WarehouseInfo::getCountryId,countryId)
               // .eq(WarehouseInfo::getWarehouseType, WarehouseTypeEnum.EGATEE_AUTO_CREATE_WAREHOUSE.getCode())
                .eq(WarehouseInfo::getIsDeleted,false));
    }

    @Override
    public Boolean checkIsRShopByWareHouseId(Long wareHouseId) {
        if(ObjectUtil.isNotNull(wareHouseId)){
            //校验身份
            WarehouseInfo warehouseInfo =  this.getById(wareHouseId);
            if(ObjectUtil.isNotNull(warehouseInfo) && ObjectUtil.isNotNull(warehouseInfo.getBelongUserId())){
                return  psiUserIdentityService.checkIsdRShopByPsiUserId(warehouseInfo.getBelongUserId());
            }
        }
        return false;
    }



}
