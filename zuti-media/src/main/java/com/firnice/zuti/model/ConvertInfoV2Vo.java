package com.firnice.zuti.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lihongwei
 * @version 1.0
 * @date 2019-12-26 11:39
 */
@Data
@NoArgsConstructor
public class ConvertInfoV2Vo {


    /**
     * 码率
     */
    private int bitRate;

    /**
     * 运动因子，1正常, 越大代表运动画面越多，一般动作比较多的设置成4
     */
    private double moveFactors;



    /**
     * 帧数
     */
    private double frameRate;

    /**
     * 最小边 480,720,1080
     */
    private ResolutionEnum resolution;

    private String filter;

    private String styleType;

    /**
     * 目标大小限制
     */
    private boolean targetSizeLimit;

    /**
     * 大小
     */
    private long targetSize;

    /**
     * 目标fps大小限制
     */
    private boolean targetFpsLimit;

    /**
     * 大小
     */
    private int targetFps;


    /**
     * 比特率设置
     */
    private BitRateProcessingModeEnum bitRateProcessingMode;



}
