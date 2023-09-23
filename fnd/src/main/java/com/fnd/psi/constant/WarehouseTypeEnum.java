package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Getter
public enum WarehouseTypeEnum {

    /**
     * 仓库类型 1：egatee自动创建仓，2,egatee自营仓,3:托管仓库,4:第三方仓库
     */
    EGATEE_AUTO_CREATE_WAREHOUSE(1, "egatee自动创建仓"),
    SELF_OPERATED_WAREHOUSE(2, "egatee自营仓"),
    MANAGED_WAREHOUSE(3, "托管仓库"),
    THIRD_PARTY_WAREHOUSE(4, "第三方仓库");

    private final Integer code;
    private final String desc;


    private static final Map<Integer, WarehouseTypeEnum> map = new HashMap<>();

    static {
        for(WarehouseTypeEnum item: WarehouseTypeEnum.values()){
            map.put(item.code,item);
        }
    }

    public static WarehouseTypeEnum valueOf(Integer code) {
        return map.get(code);
    }

}
