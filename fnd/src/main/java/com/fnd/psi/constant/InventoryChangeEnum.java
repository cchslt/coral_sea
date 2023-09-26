package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:'库存变更类型:1:增加,2:减少',
 * @See:
 */
@AllArgsConstructor
@Getter
public enum InventoryChangeEnum {

    ADD(1, "增加"),
    REDUCE(2, "减少"),
    OCCUPY(3, "占用");

    private final Integer code;
    private final String desc;


    private static final Map<Integer, InventoryChangeEnum> map = new HashMap<>();

    static {
        for(InventoryChangeEnum item: InventoryChangeEnum.values()){
            map.put(item.code,item);
        }
    }

    public static InventoryChangeEnum valueOf(Integer code) {
        return map.get(code);
    }
}
