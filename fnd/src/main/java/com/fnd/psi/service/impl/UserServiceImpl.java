package com.fnd.psi.service.impl;

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
import com.fnd.psi.dto.vo.ResultVo;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.UserMapper;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.ResultUtils;
import com.fnd.psi.utils.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    };


}
