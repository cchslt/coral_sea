package com.fnd.psi.constant;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-22 14:46
 * @Desc:
 * @See:
 */
public interface ErrorCodeConstants {

    /**
     * 用户不存在
     */
    String USER_DOESN = "1000000001";

    String USER_NOT_LOGIN = "1000000002";

    /**
     * 密码错误
     */
    String PASSWORD_ERROR = "1610000002";

    /**
     * 用户名错误
     */
    String USERNAME_ERROR = "1610000003";

    /**
     * 密碼不能為空
     */
    String PASSWORD_CANT_EMPTY = "100010004";

    /**
     * 账号被冻结
     */
    String ACCOUNT_IS_FROZEN = "1610000008";
}
