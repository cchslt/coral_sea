package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2022/2/18/018 17:42
 */
@AllArgsConstructor
@Getter
public enum UserIdentityTypeEnum {

    /**
     * (1、 国包  2、二级（DBR)  3、R)
     */
    NATIONAL_PACKAGE(1, "国包"),
    SECOND_LEVEL(2, "二级（DBR)"),
    R_SHOP(3, "R"),
    /**
     * 本地
     */
    LOCAL(4, "LOCAL"),
    /**
     * 其他
     */
    OTHER(5, "other"),
    /**
     * 跨境
     */
    CROSS_BORDER(6, "crossBorder");

    private final Integer code;
    private final String desc;


    private static final Map<Integer, UserIdentityTypeEnum> map = new HashMap<>();

    static {
        for(UserIdentityTypeEnum item: UserIdentityTypeEnum.values()){
            map.put(item.code,item);
        }
    }

    public static UserIdentityTypeEnum valueOf(Integer code) {
        return map.get(code);
    }

}
