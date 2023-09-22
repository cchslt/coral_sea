package com.fnd.psi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.user.UserLoginDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiUserListVO;
import com.fnd.psi.dto.vo.UserListDTO;
import com.fnd.psi.model.PsiUser;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 16:23
 * @Desc:
 * @See:
 */

public interface UserService extends IService<PsiUser> {

    List<PsiUser> queryUserList();

    ResultVo<PageDTO<PsiUser>> pageUserList();

    /**
     * 登录信息
     * @param userLoginDTO
     * @return
     */
    ResultVo userLogin(UserLoginDTO userLoginDTO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    PsiUserInfoDTO info(Long userId);

    /**
     * 员工账号
     * @param userListDTO
     * @return
     */
    ResultVo<IPage<PsiUserListVO>> userList(UserListDTO userListDTO);
}
