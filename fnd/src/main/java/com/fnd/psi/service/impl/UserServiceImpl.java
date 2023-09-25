package com.fnd.psi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.constant.GlobalErrorCodeConstants;
import com.fnd.psi.constant.UserSourceTypeEnum;
import com.fnd.psi.constant.UserStatusEnum;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.user.UserLoginDTO;
import com.fnd.psi.dto.vo.*;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.UserMapper;
import com.fnd.psi.model.PsiInWarehouseUserRelation;
import com.fnd.psi.model.PsiOutWarehouseUserRelation;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.*;
import com.fnd.psi.utils.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private WarehouseUserRelationService warehouseUserRelationService;
    @Autowired
    private PsiOutWarehouseUserRelationService psiOutWarehouseUserRelationService;
    @Autowired
    private PsiInWarehouseUserRelationService psiInWarehouseUserRelationService;
    @Autowired
    Encryption encode;
    @Autowired
    private UserRoleService userRoleService;

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

        Page<PsiUser> page = new Page<>(1, 10);
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        Page<PsiUser> selectPage = userMapper.selectPage(page, queryWrapper);

        PageDTO<PsiUser> resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);
        resultPage.setPages(selectPage.getPages());

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
        List<PsiUserRoleVO> roles = Lists.newArrayList();
        List<PermissionDTO> menuData = Lists.newArrayList();
        List<String> permissions = Lists.newArrayList();
        List<Long> roleIds = Lists.newArrayList();
        PsiUser userLogin = null;
        if (Objects.isNull(userId)){
            userLogin  = CopyBeanUtils.convert(FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser(),PsiUser.class);
        }else {
            userLogin =  getById(userId);
        }
        PsiUserInfoDTO psiUserInfoDTO = new PsiUserInfoDTO();
        psiUserInfoDTO.setUser(CopyBeanUtils.convert(userLogin,PsiUserDTO.class));
        final List<PsiUserRoleVO> roleInfoByUserId = roleService.getRoleInfoByUserId(Sets.newHashSet(userLogin.getId()));
        if (!CollectionUtils.isEmpty(roleInfoByUserId)){
            roles = roleInfoByUserId;
            roleIds = roleInfoByUserId.stream().map(PsiUserRoleVO::getId).collect(Collectors.toList());
        }
        if (!CollectionUtils.isEmpty(roleIds)){
            final HashMap<String, List<PermissionDTO>> rolePermissionByRoles = permissionService.getRolePermissionByRoles(roleIds, psiUserInfoDTO);
            menuData = rolePermissionByRoles.get("routes");
            final List<PermissionDTO> permissionDTOS = rolePermissionByRoles.get("button");
            permissions = permissionDTOS.stream().map(PermissionDTO::getPath).collect(Collectors.toList());
        }

        psiUserInfoDTO.setRoles(roles);
        psiUserInfoDTO.setMenuData(menuData);
        psiUserInfoDTO.setPermissions(permissions);
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


    @Override
    public ResultVo updateUserFrozen(UserDTO userDTO) {
        final Date date = new Date();
        final PsiUserInfoDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO();
        final PsiUser selectPsiUser = getById(userDTO.getId());
        if (!currentUser.getUser().getBelongUserId().equals(selectPsiUser.getBelongUserId())){
            throw new XXException(GlobalErrorCodeConstants.USER_HAS_NO_PERMISSION);
        }
        if (selectPsiUser.getLevel()==0 && userDTO.getIsFrozen().equals(UserStatusEnum.DEFAULT.getCode())){
            throw new XXException(ErrorCodeConstants.ADMINISTRATORS_CANNOT_BE_FROZEN);
        }
        PsiUser user = new PsiUser();
        user.setId(userDTO.getId());
        user.setUserStatus(userDTO.getIsFrozen());
        user.setGmtModified(date);
        updateById(user);
        return ResultVoUtil.success();
    }

    @Override
    @Transactional
    public ResultVo addUser(UserDTO userDTO) {
        final Date date = new Date();
        final PsiUserDTO userLogin = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        final List<PsiUser> queryPsiUser = list(new LambdaQueryWrapper<PsiUser>().
                eq(PsiUser::getUserName, userDTO.getUserName()).
                eq(PsiUser::getIsDeleted, false));
        if (!CollectionUtils.isEmpty(queryPsiUser)){
            throw new XXException(ErrorCodeConstants.USER_ALREADY_EXISTS);
        }
        PsiUser addPsiUser = new PsiUser();//待新增用户
        BeanUtils.copyProperties(userDTO,addPsiUser);
        Long belongUserId = userLogin.getBelongUserId();
        addPsiUser.setBelongUserId(belongUserId);
        addPsiUser.setGmtCreate(date);
        addPsiUser.setUserStatus(userDTO.getIsFrozen());
        addPsiUser.setLevel(1);
        addPsiUser.setCountryId(userLogin.getCountryId());
        addPsiUser.setCountryCode(userLogin.getCountryCode());
        addPsiUser.setUserType(userLogin.getUserType());
        addPsiUser.setIsDeleted(false);
        addPsiUser.setPassword(encode.encode(userDTO.getPassword()));
        addPsiUser.setCreateUserId(userLogin.getId());
        addPsiUser.setSourceType(UserSourceTypeEnum.DEFAULT.getCode());
        addPsiUser.setGmtModified(date);
        save(addPsiUser);
        userRoleRelation(addPsiUser.getId(),userDTO.getRoles());
        userWarehouseRelation(addPsiUser.getId(),userDTO.getWarehouseIds());
        userOutWarehouseRelation(addPsiUser.getId(),userDTO.getOutWarehouseIds());
        userInWarehouseRelation(addPsiUser.getId(),userDTO.getInWarehouseIds());
        return ResultVoUtil.success();
    }

    /**
     * 用户绑角色
     * @param userId
     * @param roles
     */
    public void userRoleRelation(Long userId,List<Long> roles){
        if (CollectionUtils.isEmpty(roles)){
            roles = Lists.newArrayList();
        }
        userRoleService.userRoleRelation(userId,roles);
    }

    public void userWarehouseRelation(Long userId,List<Long> warehouseIds){
        //绑定仓库
        if (CollectionUtils.isEmpty(warehouseIds)){
            warehouseIds = Lists.newArrayList();
        }
        warehouseUserRelationService.userWarehouseRelation(userId,warehouseIds);
    }

    private void userOutWarehouseRelation(Long userId, List<Long> outWarehouseIds) {
        //绑定仓库
        if (CollectionUtils.isEmpty(outWarehouseIds)){
            outWarehouseIds = Lists.newArrayList();
        }
        psiOutWarehouseUserRelationService.addPsiOutWarehouseUserRelationAndDeleteOld(userId,outWarehouseIds);
    }

    private void userInWarehouseRelation(Long userId, List<Long> inWarehouseIds) {
        //绑定仓库
        if (CollectionUtils.isEmpty(inWarehouseIds)){
            inWarehouseIds = Lists.newArrayList();
        }
        psiInWarehouseUserRelationService.addPsiInWarehouseUserRelationAndDeleteOld(userId,inWarehouseIds);
    }

    @Override
    public ResultVo updateUser(UserDTO userDTO) {
        if (Objects.isNull(userDTO.getId())){
            throw new XXException(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        final Date date = new Date();
        final PsiUserDTO userLogin = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        final List<PsiUser> queryPsiUser = list(new LambdaQueryWrapper<PsiUser>().
                eq(PsiUser::getUserName, userDTO.getUserName()).
                notIn(PsiUser::getId,userDTO.getId()).
                eq(PsiUser::getIsDeleted, false));

        if (!CollectionUtils.isEmpty(queryPsiUser)){
            throw new XXException(ErrorCodeConstants.USER_ALREADY_EXISTS);
        }
        PsiUser getPsiUser = getById(userDTO.getId());
        if (!userLogin.getBelongUserId().equals(getPsiUser.getBelongUserId())){
            throw new XXException(GlobalErrorCodeConstants.USER_HAS_NO_PERMISSION);
        }
        if (getPsiUser.getLevel()==0 &&userDTO.getIsFrozen()==UserStatusEnum.DEFAULT.getCode()){
            throw new XXException(ErrorCodeConstants.ADMINISTRATORS_CANNOT_BE_FROZEN);
        }
        PsiUser addPsiUser = new PsiUser();
        addPsiUser.setId(getPsiUser.getId());
        addPsiUser.setNickName(userDTO.getNickName());
        addPsiUser.setPhone(userDTO.getPhone());
        addPsiUser.setUserStatus(userDTO.getIsFrozen());
        addPsiUser.setGmtModified(date);
        updateById(addPsiUser);
        userRoleRelation(addPsiUser.getId(),userDTO.getRoles());
        userWarehouseRelation(addPsiUser.getId(),userDTO.getWarehouseIds());
        userOutWarehouseRelation(addPsiUser.getId(),userDTO.getOutWarehouseIds());
        userInWarehouseRelation(addPsiUser.getId(),userDTO.getInWarehouseIds());
        return ResultVoUtil.success();
    }


    @Override
    public ResultVo updatePassword(UserUpdatePasswordDTO userDTO) {
        final Date date = new Date();
        if (Objects.isNull(userDTO)||Objects.isNull(userDTO.getUserId())){
            throw new XXException(GlobalErrorCodeConstants.INVALID_PARAMETER);
        }
        if (Objects.isNull(userDTO.getPassword())||Objects.isNull(userDTO.getConfirmPassword())){
            throw new XXException(ErrorCodeConstants.INCORRECT_PASSWORD_INPUT);
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new XXException(ErrorCodeConstants.INCONSISTENT_PASSWORD_INPUT);
        }
        final PsiUser psiUser = getById(userDTO.getUserId());
        if (Objects.isNull(psiUser)){
            throw new XXException(ErrorCodeConstants.USER_DOESN);
        }
        PsiUser updatePsiUser = new PsiUser();
        updatePsiUser.setId(userDTO.getUserId());
        updatePsiUser.setPassword(encode.encode(userDTO.getPassword()));
        updatePsiUser.setGmtModified(date);
        updateById(updatePsiUser);
        return ResultVoUtil.success();
    }


}
