package com.firnice.zuti.interfaces.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class ProviderTestDTO implements Serializable {
    // ID
    private int id;
    // 名字
    private String name;
    // 序号
    private Integer number;
}