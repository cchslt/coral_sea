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


    /**
     * 管理员不能被冻结
     */
    String ADMINISTRATORS_CANNOT_BE_FROZEN = "1610000010";

    /**
     * 用户已经存在
     */
    String USER_ALREADY_EXISTS = "1610000005";

    /**
     *  修改密码输入密码有误
     */
    String INCORRECT_PASSWORD_INPUT = "1610000006";

    /**
     *  输入密码不一致
     */
    String INCONSISTENT_PASSWORD_INPUT = "1610000007";

    /**
     * token过期或不正确
     */
    String TOKEN_IS_ERROR = "161000011";



    /**
     * 分页参数不能为空
     */
    String PAGE_PARAMETER_CAN_NOT_BE_NULL = "1602000001";
    /**
     * 仓库名不能为空
     */
    String WAREHOUSE_NAME_CAN_NOT_BE_NULL = "1602000002";

    /**
     * 未查询到仓库信息
     */
    String NO_WAREHOUSE_INFORMATION_FOUND= "1602000003";

    //仓库发货区域不能为空
    String WAREHOUSE_SHOPPING_REGION_CAN_NOT_BE_EMPTY= "1602000004";





    String PRODUCT_CODE_IS_EXIT = "2000000001";
    String PRODUCT_CODE_IS_NOT_EXIT = "2000000002";
    String PRODUCT_CODE_IS_NOT_EQUAL = "2000000003";

}
