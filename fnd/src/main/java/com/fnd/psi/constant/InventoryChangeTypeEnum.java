package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum InventoryChangeTypeEnum {
	/**
	 * 可售库存
	 */
	SELLABLE_STOCK(1, "SELLABLE_STOCK"),
	/**
	 * 可用库存
	 */
	AVAILABLE_STOCK(2, "AVAILABLE_STOCK"),
	/**
	 * 待入库库存
	 */
	PENDING_IN_STOCK(3, "PENDING_IN_STOCK"),

	;
	private static final Map<Integer, InventoryChangeTypeEnum> INVENTORY_MAP = new HashMap<>(16);

	static {
		InventoryChangeTypeEnum[] enums = InventoryChangeTypeEnum.values();
		for (InventoryChangeTypeEnum anEnum : enums) {
			INVENTORY_MAP.put(anEnum.getCode(), anEnum);
		}
	}

	private final Integer code;
	private final String desc;

	public static InventoryChangeTypeEnum getEnumByCode(Integer code) {
		return INVENTORY_MAP.get(code);
	}

	public static String getDescByCode(Integer code) {
		InventoryChangeTypeEnum anEnum = INVENTORY_MAP.get(code);
		if (anEnum == null) {
			return null;
		} else {
			return anEnum.getDesc();
		}
	}


}
