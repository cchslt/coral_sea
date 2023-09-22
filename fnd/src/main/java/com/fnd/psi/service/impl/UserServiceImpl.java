package com.fnd.psi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.constant.UserStatusEnum;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.user.UserLoginDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiUserListVO;
import com.fnd.psi.dto.vo.PsiUserRoleVO;
import com.fnd.psi.dto.vo.UserListDTO;
import com.fnd.psi.dto.vo.WarehouseUserRelationInfoDTO;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.UserMapper;
import com.fnd.psi.model.PsiInWarehouseUserRelation;
import com.fnd.psi.model.PsiOutWarehouseUserRelation;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.ResultUtils;
import com.fnd.psi.utils.ResultVoUtil;
import com.fnd.psi.utils.TokenUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 18:20
 * @Desc:
 * @See:
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, PsiUser> implements UserService {

    ResultUtils resultUtils;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public List<PsiUser> queryUserList() {
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        final List<PsiUser> psiUsers = userMapper.selectList(queryWrapper);

        log.info("sdfsdfdsf");
        return psiUsers;
    }

    @Override
    public ResultVo<PageDTO<PsiUser>> pageUserList() {

        IPage<PsiUser> page = new Page<>(1, 10);
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        IPage<PsiUser> selectPage = userMapper.selectPage(page, queryWrapper);

        PageDTO<PsiUser> resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);

        return resultUtils.returnSuccess(resultPage);
    }

    @Override
    public ResultVo userLogin(UserLoginDTO userLoginDTO) {
        if (Objects.isNull(userLoginDTO)){
            throw new XXException(ErrorCodeConstants.USER_DOESN);
        }
        if (Objects.isNull(userLoginDTO.getUsername())){
            throw new XXException(ErrorCodeConstants.USERNAME_ERROR);
        }
        if (Objects.isNull(userLoginDTO.getPassword())){
            throw new XXException(ErrorCodeConstants.PASSWORD_CANT_EMPTY);
        }
        final PsiUser psiUser = getOne(new LambdaQueryWrapper<PsiUser>().
                eq(PsiUser::getUserName, userLoginDTO.getUsername()).
                eq(PsiUser::getIsDeleted, false));
        if (Objects.isNull(psiUser)) {
            throw new XXException(ErrorCodeConstants.USER_DOESN);
        }
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), psiUser.getPassword())) {
            //密码错误
            throw new XXException(ErrorCodeConstants.PASSWORD_ERROR);
        }
        if (psiUser.getUserStatus().equals(UserStatusEnum.DEFAULT.getCode())){
            throw new XXException(ErrorCodeConstants.ACCOUNT_IS_FROZEN);
        }
        PsiUserInfoDTO psiUserInfoDTO = info(psiUser.getId());
        String token = TokenUtil.sign(psiUserInfoDTO);
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", token);
        return resultUtils.success(tokenMap);
    }

    public PsiUserInfoDTO info(Long userId) {
        PsiUserInfoDTO psiUserInfoDTO = new PsiUserInfoDTO();

        PsiUser psiUser = new LambdaQueryChainWrapper<>(userMapper)
                .eq(PsiUser::getIsDeleted, 0)
                .eq(PsiUser::getId, userId)
                .one();

        psiUserInfoDTO.setUser(CopyBeanUtils.convert(psiUser, PsiUserDTO.class));

        return psiUserInfoDTO;
    }

    @Override
    public ResultVo<IPage<PsiUserListVO>> userList(UserListDTO userListDTO) {
        final PsiUserInfoDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO();
        IPage<PsiUser> page = new Page<>(userListDTO.getCurrent(), userListDTO.getPageSize());
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        queryWrapper.eq(PsiUser::getCountryId,user.getUser().getCountryId());
        queryWrapper.eq(PsiUser::getBelongUserId, user.getUser().getBelongUserId());
        if (StrUtil.isNotEmpty(userListDTO.getKeywords())){
            final String keywords = userListDTO.getKeywords();
            queryWrapper.and(i -> i
                    .or().like(PsiUser::getPhone, keywords)
                    .or().like(PsiUser::getUserName, keywords)
                    .or().like(PsiUser::getNickName, keywords));
        }
        queryWrapper.orderByDesc(PsiUser::getGmtCreate);
        IPage<PsiUser> recordPage = userMapper.selectPage(page, queryWrapper);
        final List<PsiUser> records = recordPage.getRecords();
        Map<Long,List<PsiUserRoleVO>> psiUserRole = Maps.newHashMap();
        Map<Long,List<WarehouseUserRelationInfoDTO>> warehouseIdByUserIdMap = Maps.newHashMap();
        Map<Long,List<PsiOutWarehouseUserRelation>> outWarehouseIdByUserIdMap = Maps.newHashMap();
        Map<Long,List<PsiInWarehouseUserRelation>> inWarehouseIdByUserIdMap = Maps.newHashMap();

        final List<PsiUserListVO> psiUserListVOPage = Lists.newArrayList();
        IPage<PsiUserListVO> recordVOIPage = new Page<>();
        recordVOIPage.setPages(recordPage.getPages());
        recordVOIPage.setTotal(recordPage.getTotal());
        recordVOIPage.setSize(recordPage.getSize());
        if (CollectionUtils.isEmpty(records)){
            recordVOIPage.setRecords(psiUserListVOPage);
            return ResultVoUtil.success(recordVOIPage);
        }
        final Set<Long> userIds = records.stream().map(PsiUser::getId).collect(Collectors.toSet());
        final List<PsiUserRoleVO> roleInfoByUserId = roleService.getRoleInfoByUserId(userIds);
        if (!CollectionUtils.isEmpty(roleInfoByUserId)){
            psiUserRole = roleInfoByUserId.stream().collect(Collectors.groupingBy(PsiUserRoleVO::getUserId));
        }
        final List<WarehouseUserRelationInfoDTO> warehouseInfoByUserIds = warehouseUserRelationService.getWarehouseInfoByUserIds(userIds);
        if (!CollectionUtils.isEmpty(warehouseInfoByUserIds)){
            warehouseIdByUserIdMap = warehouseInfoByUserIds.stream().collect(Collectors.groupingBy(WarehouseUserRelationInfoDTO::getUserId));
        }
        final List<PsiOutWarehouseUserRelation> psiOutWarehouseUserRelationList = psiOutWarehouseUserRelationService.getWarehouseIdByUserIds(userIds);
        final List<PsiInWarehouseUserRelation> psiInWarehouseUserRelationList = psiInWarehouseUserRelationService.getWarehouseIdByUserIds(userIds);

        if (!CollectionUtils.isEmpty(psiOutWarehouseUserRelationList)){
            outWarehouseIdByUserIdMap = psiOutWarehouseUserRelationList.stream().collect(Collectors.groupingBy(PsiOutWarehouseUserRelation::getUserId));
        }
        if (!CollectionUtils.isEmpty(psiInWarehouseUserRelationList)){
            inWarehouseIdByUserIdMap = psiInWarehouseUserRelationList.stream().collect(Collectors.groupingBy(PsiInWarehouseUserRelation::getUserId));
        }
        final Map<Long, List<PsiUserRoleVO>> finalPsiUserRole = psiUserRole;

        Map<Long, List<WarehouseUserRelationInfoDTO>> finalWarehouseIdByUserIdMap = warehouseIdByUserIdMap;
        Map<Long, List<PsiOutWarehouseUserRelation>> finalOutWarehouseIdByUserIdMap = outWarehouseIdByUserIdMap;
        for (PsiUser x : records) {
            List<PsiUserRoleVO> psiUserRoleVOS = finalPsiUserRole.get(x.getId());
            final List<WarehouseUserRelationInfoDTO> warehouseUserRelations = finalWarehouseIdByUserIdMap.get(x.getId());
            final List<PsiOutWarehouseUserRelation> psiOutWarehouseUserRelationList1 = finalOutWarehouseIdByUserIdMap.get(x.getId());
            List<PsiInWarehouseUserRelation> inWarehouseUserRelationList = inWarehouseIdByUserIdMap.getOrDefault(x.getId(), Lists.newArrayList());
            PsiUserListVO psiUserListVO = new PsiUserListVO();
            BeanUtils.copyProperties(x,psiUserListVO);
            psiUserListVO.setIsFrozen(x.getUserStatus());
            psiUserListVO.setRoles(psiUserRoleVOS);
            if (!CollectionUtils.isEmpty(psiOutWarehouseUserRelationList1)){
                psiUserListVO.setOutWarehouseIds(psiOutWarehouseUserRelationList1.stream().map(PsiOutWarehouseUserRelation::getWarehouseId).collect(Collectors.toList()));
            }
            if (!CollectionUtils.isEmpty(inWarehouseUserRelationList)){
                psiUserListVO.setInWarehouseIds(inWarehouseUserRelationList.stream().map(PsiInWarehouseUserRelation::getWarehouseId).collect(Collectors.toList()));
            }
            if (!CollectionUtils.isEmpty(warehouseUserRelations)){
                psiUserListVO.setWarehouseIds(warehouseUserRelations.stream().map(WarehouseUserRelationInfoDTO::getWarehouseId).collect(Collectors.toList()));
                psiUserListVO.setWarehouseInfos(warehouseUserRelations);
            }
            psiUserListVOPage.add(psiUserListVO);
        }
        recordVOIPage.setRecords(psiUserListVOPage);
        return ResultVoUtil.success(recordVOIPage);
    }


}
