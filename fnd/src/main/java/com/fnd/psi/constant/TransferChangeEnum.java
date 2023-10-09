package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Getter
public enum TransferChangeEnum {

    INKJET_PRINTING(1, "喷绘打印"),
    MOUNT(2, "托裱"),
    FRAMED_MOUNT(3, "装框呈裱"),
    LOGISTICS_TRANSPORTATION(4, "物流运输中");

    private final Integer code;
    private final String desc;


    private static final Map<Integer, TransferChangeEnum> map = new HashMap<>();

    static {
        for(TransferChangeEnum item: TransferChangeEnum.values()){
            map.put(item.code,item);
        }
    }

    public static TransferChangeEnum valueOf(Integer code) {
        return map.get(code);
    }
}
