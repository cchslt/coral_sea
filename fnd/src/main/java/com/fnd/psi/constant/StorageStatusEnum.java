package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xu_xin
 * @Date: 2022/2/23/023 15:33
 */
@AllArgsConstructor
@Getter
public enum StorageStatusEnum {

    /**
     * 入库状态,1未入库、2部分入库、3全部入库、0已经关闭 、10 取消
     */
    CLOSE(0, "已关闭"),
    NOT_WAREHOUSED(1, "未入库"),
    PARTIAL_WAREHOUSING(2, "部分入库"),
    ALL_WAREHOUSING(3, "全部入库"),
    CANCEL(10,"取消");

    private final Integer code;
    private final String desc;


    private static final Map<Integer, StorageStatusEnum> map = new HashMap<>();

    static {
        for (StorageStatusEnum item : StorageStatusEnum.values()) {
            map.put(item.code, item);
        }
    }

    public static StorageStatusEnum valueOf(Integer code) {
        return map.get(code);
    }

}
