package com.fnd.psi.constant;


public interface CommonConstant {


    /**----------全局错误码------------**/
    /**
     * 成功标记
     */
    Integer SUCCESS = 1;
    /**
     * 成功message
     */
    String SUCCESS_MESSAGE = "success";

    /**
     * 失败标记
     */
    Integer FAIL = -1;
    /**
     * 失败message
     */
    String FAIL_MESSAGE = "fail";

    /**----------常用符号--避免写错----------**/

    /**
     * 下滑线
     */
    String UNDERLINE = "_";

    /**
     * 英文逗号
     */
    String COMMA_EN = ",";
    /**
     * 空格
     */
    String SPACE = " ";


    /**----------常用常量------------**/
    /**
     * 是否删除->未删除
     */
    Integer IS_DELETED_FALSE = 0;
    /**
     * 是否删除->已删除
     */
    Integer IS_DELETED_TRUE = 1;

    /**
     * header 中SYSTEM_TYPE
     */
    String SYSTEM_TYPE = "SYSTEM_TYPE";


    /**
     * 数据权限过滤类型
     */
    String DATA_FILTER_TYPE = "DATA_FILTER_TYPE";

    /**
     * header 中租户VERSION
     */
    String VERSION = "VERSION";

    /**
     * 请求记录ID
     */
    String REQUEST_ID = "ADMIN_REQUEST_ID";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";


}
