package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.CommonConstant;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.product.PsiProductSkuDTO;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.dto.vo.PsiProductSkuVO;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.PsiInventoryMapper;
import com.fnd.psi.mapper.PsiProductSkuMapper;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.model.PsiProductSku;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.PsiProductSkuService;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.PSIBaseUtils;
import com.fnd.psi.utils.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:41
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiProductSkuService")
@AllArgsConstructor
public class PsiProductSkuServiceImpl extends ServiceImpl<PsiProductSkuMapper, PsiProductSku> implements PsiProductSkuService {

    private ResultUtils resultUtils;
    private UserService userService;
    private PsiInventoryMapper psiInventoryMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiProductSkuDTO save(PsiProductSkuDTO psiProductSku) {
        if (psiProductSku.getId() != null) {
            return update(psiProductSku);
        }
        PsiProductSku exitSku = findBySkuCode(psiProductSku.getSkuCode());
        if (ObjectUtil.isNotNull(exitSku)) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_EXIT);
        }

        PsiProductSku productSku = CopyBeanUtils.convert(psiProductSku, PsiProductSku.class);
        final Long userId = FndSecurityContextUtil.getContext().getId();
        productSku.setCreateUserId(userId);
        productSku.setModifyUserId(userId);
        this.save(productSku);

        return CopyBeanUtils.convert(productSku, PsiProductSkuDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiProductSkuDTO update(PsiProductSkuDTO psiProductSku) {
        PsiProductSku productSku = this.getById(psiProductSku.getId());
        if (ObjectUtil.isNull(productSku)) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_NOT_EXIT);
        }
        if (!psiProductSku.getSkuCode().equals(productSku.getSkuCode())) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_NOT_EQUAL);
        }

        productSku.setSkuProductName(psiProductSku.getSkuProductName());
        final Long userId = FndSecurityContextUtil.getContext().getId();
        productSku.setModifyUserId(userId);

        return psiProductSku;
    }

    @Override
    public ResultVo delete(Long id) {
        LambdaQueryWrapper<PsiProductSku> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiProductSku::getId, id);
        queryWrapper.eq(PsiProductSku::getIsDeleted, CommonConstant.IS_DELETED_FALSE);

        List<PsiProductSku> productSkus = this.list(queryWrapper);
        if (CollUtil.isEmpty(productSkus)) {
            return resultUtils.success();
        }

        final PsiUserDTO psiUserDTO = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        lambdaUpdate().set(PsiProductSku::getIsDeleted, CommonConstant.IS_DELETED_TRUE)
                .set(PsiProductSku::getModifyUserId, psiUserDTO.getId())
                .set(PsiProductSku::getGmtModified, new Date())
                .eq(PsiProductSku::getId, id)
                .eq(PsiProductSku::getIsDeleted,CommonConstant.IS_DELETED_FALSE)
                .update();

        return resultUtils.success();
    }

    @Override
    public ResultVo<PsiProductSkuDTO> detail(Long id) {
        LambdaQueryWrapper<PsiProductSku> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiProductSku::getId, id);
        queryWrapper.eq(PsiProductSku::getIsDeleted, CommonConstant.IS_DELETED_FALSE);

        final PsiProductSku psiProductSku = this.getOne(queryWrapper);

        PsiProductSkuDTO psiProductSkuDTO = CopyBeanUtils.convert(psiProductSku, PsiProductSkuDTO.class);

        Set<Long> userIds = CollUtil.newHashSet(psiProductSkuDTO.getCreateUserId(), psiProductSkuDTO.getModifyUserId());
        Map<Long, String> userMap = userService.queryByUserIds(userIds)
                .stream().collect(Collectors.toMap(PsiUser::getId, PsiUser::getUserName));

        psiProductSkuDTO.setCreateUserName(userMap.getOrDefault(psiProductSkuDTO.getCreateUserId(), ""));
        psiProductSkuDTO.setModifyUserName(userMap.getOrDefault(psiProductSkuDTO.getModifyUserId(), ""));

        return resultUtils.returnSuccess(psiProductSkuDTO);
    }


    public PsiProductSku findBySkuCode(String skuCode) {
        LambdaQueryWrapper<PsiProductSku> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(PsiProductSku::getSkuCode, skuCode);
        lambdaQueryWrapper.eq(PsiProductSku::getIsDeleted, 0);

        return this.getOne(lambdaQueryWrapper);
    }


    @Override
    public List<PsiProductSkuDTO> list(PsiProductSkuVO productSkuVO) {
        LambdaQueryWrapper<PsiProductSku> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuCode()), PsiProductSku::getSkuCode, productSkuVO.getSkuCode());
        lambdaQueryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuProductName()), PsiProductSku::getSkuProductName, productSkuVO.getSkuProductName());
        lambdaQueryWrapper.eq(PsiProductSku::getIsDeleted, 0);
        lambdaQueryWrapper.orderByDesc(PsiProductSku::getGmtCreate);

        return CopyBeanUtils.copyList(baseMapper.selectList(lambdaQueryWrapper), PsiProductSkuDTO.class);
    }

    @Override
    public PageDTO<PsiProductSkuDTO> listPage(PsiProductSkuVO productSkuVO) {
        Page<PsiProductSku> page = PSIBaseUtils.buildPageByQuery(productSkuVO);

        LambdaQueryWrapper<PsiProductSku> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuCode()), PsiProductSku::getSkuCode, productSkuVO.getSkuCode());
        queryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuProductName()), PsiProductSku::getSkuProductName, productSkuVO.getSkuProductName());
        queryWrapper.eq(PsiProductSku::getIsDeleted, 0);

        Page<PsiProductSku> selectPage = baseMapper.selectPage(page, queryWrapper);

        PageDTO<PsiProductSkuDTO> resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);
        resultPage.setRecords(CopyBeanUtils.copyList(selectPage.getRecords(), PsiProductSkuDTO.class));
        resultPage.setPages(selectPage.getPages());

        Set<Long> userIds = CollUtil.newHashSet();
        Set<Long> skuIds = CollUtil.newHashSet();
        selectPage.getRecords().forEach(x -> {
            userIds.add(x.getCreateUserId());
            userIds.add(x.getModifyUserId());

            skuIds.add(x.getId());
        });

        Map<Long, String> userMap = userService.queryByUserIds(userIds)
                .stream().collect(Collectors.toMap(PsiUser::getId, PsiUser::getUserName));

        //先这样吧
        Map<Long, Integer> skuInventoryMap = CollUtil.newHashMap();
        if (productSkuVO.getWarehouseId() != null) {
            LambdaQueryWrapper<PsiInventory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(PsiInventory::getWarehouseId, productSkuVO.getWarehouseId());
            lambdaQueryWrapper.in(PsiInventory::getProductSkuId, skuIds);
            lambdaQueryWrapper.eq(PsiInventory::getIsDeleted, 0);

            skuInventoryMap = psiInventoryMapper.selectList(lambdaQueryWrapper)
                    .stream().collect(Collectors.toMap(PsiInventory::getProductSkuId, PsiInventory::getSellableQuantity));
        }

        for(PsiProductSkuDTO x : resultPage.getRecords()){
            x.setCreateUserName(userMap.get(x.getCreateUserId()));
            x.setModifyUserName(userMap.get(x.getModifyUserId()));
            x.setSellableQuantity(skuInventoryMap.get(x.getId()));
        }
        return resultPage;
    }

    @Override
    public List<PsiProductSku> findBySkuCodeList(List<String> skuCodeList) {
        LambdaQueryWrapper<PsiProductSku> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.in(PsiProductSku::getSkuCode, skuCodeList);
        lambdaQueryWrapper.eq(PsiProductSku::getIsDeleted, 0);

        return Optional.ofNullable(list(lambdaQueryWrapper)).orElse(CollUtil.newArrayList());
    }


}
