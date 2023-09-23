package com.fnd.psi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserSourceTypeEnum {

    /**
     * egatee
     */
    DEFAULT(0, "默认"),
    EGATEE(1, "EGATEE"),
    OTHER(2, "其他");

    private final Integer code;
    private final String desc;

}
