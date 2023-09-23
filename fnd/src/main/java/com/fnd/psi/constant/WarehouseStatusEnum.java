package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Skipper
 * @Date: 2022/2/22/022 19:44
 * @Desc:
 * @See:
 */
@AllArgsConstructor
@Getter
public enum WarehouseStatusEnum {


    FORBIDDEN( "禁用",0),
    ENABLE( "启用",1);

    private final String enumName;
    private final Integer enumValue;


    private static final Map<Integer, WarehouseStatusEnum> map = new HashMap<>();

    static {
        for(WarehouseStatusEnum item: WarehouseStatusEnum.values()){
            map.put(item.enumValue,item);
        }
    }
    public static WarehouseStatusEnum valueOf(Integer code) {
        return map.get(code);
    }



}
