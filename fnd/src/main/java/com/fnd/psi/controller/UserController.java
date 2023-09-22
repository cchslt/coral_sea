package com.fnd.psi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.user.UserLoginDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiUserListVO;
import com.fnd.psi.dto.vo.UserDTO;
import com.fnd.psi.dto.vo.UserListDTO;
import com.fnd.psi.dto.vo.UserUpdatePasswordDTO;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-22 14:37
 * @Desc:
 * @See:
 */
@Api(value = "用户信息", tags = "用户信息")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private ResultUtils resultUtils;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.userLogin(userLoginDTO);
    }

    /**
     * 获取当前用户全部信息
     * security 获取用户接口信息
     *
     * @return 用户信息
     */
    @FndPreAuthorize
    @ApiOperation("获取用户详情")
    @GetMapping(value = {"/info"})
    public ResultVo<PsiUserInfoDTO> info() {
        return resultUtils.returnSuccess(userService.info(null));
    }


    /**
     * 员工账户
     * @return
     */
    @FndPreAuthorize
    @ApiOperation("员工账号列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultVo<IPage<PsiUserListVO>> userList(@RequestBody UserListDTO userListDTO) {
        return userService.userList(userListDTO);
    }

    /**
     * 员工账户
     * @return
     */
    @FndPreAuthorize
    @ApiOperation("冻结员工账号")
    @RequestMapping(value = "/updateFrozen", method = RequestMethod.POST)
    public ResultVo updateUserFrozen(@RequestBody UserDTO userDTO) {
        return userService.updateUserFrozen(userDTO);
    }


    /**
     * 新增用户
     * @return
     */
    @FndPreAuthorize
    @ApiOperation("add员工")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResultVo addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }


    /**
     * 新增用户
     * @return
     */
    @FndPreAuthorize
    @ApiOperation("update员工")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResultVo updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    /**
     * 修改用户密码
     * @return
     */
    @FndPreAuthorize
    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResultVo updatePassword(@RequestBody UserUpdatePasswordDTO userDTO) {
        return userService.updatePassword(userDTO);
    }

}
