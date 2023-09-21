package com.fnd.psi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.vo.ResultVo;
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
}
