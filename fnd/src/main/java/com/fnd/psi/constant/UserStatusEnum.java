package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserStatusEnum {

    /**
     * egatee
     */
    DEFAULT(0, "禁用"),
    AVAILABLE(1, "正常");

    private final Integer code;
    private final String desc;
}
