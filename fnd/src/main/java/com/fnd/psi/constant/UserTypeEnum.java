package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Getter
public enum UserTypeEnum {

    /**
     * 用户类型（0 平台管理 1 商家管理员 ）
     */
    PLATFORM_MANAGEMENT(0L, "平台管理"),
    BUSINESS_MANAGEMENT(1L, "商家管理员");

    private final Long code;
    private final String desc;


    private static final Map<Long, UserTypeEnum> map = new HashMap<>();

    static {
        for(UserTypeEnum item: UserTypeEnum.values()){
            map.put(item.code,item);
        }
    }

    public static UserTypeEnum valueOf(Integer code) {
        return map.get(code);
    }

}
