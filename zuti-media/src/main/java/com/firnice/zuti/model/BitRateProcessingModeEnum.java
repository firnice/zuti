package com.firnice.zuti.model;

import lombok.ToString;

/**
 * 比特率处理方式与大小限制冲突，大小限制优先级比较高
 */
@ToString
public enum BitRateProcessingModeEnum {

    USER_SETTING(1,"用户设置固定的比特率"),
    MOVE_FACTORS(2,"通过设置的运动因子自动计算"),
    _DEFAULT(0,"默认不改动"),

    ;

    private Integer code;

    private String desc;

    BitRateProcessingModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
