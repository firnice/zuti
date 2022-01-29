package com.firnice.zuti.model;

import lombok.ToString;

@ToString
public enum TransferToolsEnum {

    FFMPEG(1),
    JAVACV(2),

    ;


    /**
     *
     */
    public int code;

    TransferToolsEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
