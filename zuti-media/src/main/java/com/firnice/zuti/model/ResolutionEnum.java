package com.firnice.zuti.model;

import lombok.ToString;

@ToString
public enum ResolutionEnum {

    R_480P(480),
    R_720P(720),
    R_1080P(1080),
    R_DEFAULT(0),

    ;


    /**
     * 最小边
     */
    public int code;

    ResolutionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
