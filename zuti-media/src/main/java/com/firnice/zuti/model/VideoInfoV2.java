package com.firnice.zuti.model;

import lombok.Data;

/**
 *
 */
@Data
public class VideoInfoV2 {


    /**
     * ossName
     */
    private String ossName;


    /**
     * 资源大小
     */
    private Long mediaSize;

    private Long originMediaSize;


    private Integer mediaWidth;

    private Integer originMediaWidth;


    private Integer mediaHeight;

    private Integer originMediaHeight;



    /**
     * 时长单位秒
     */
    private double duration;

    private double originDuration;

    /**
     * 视频码率 单位kb/s
     */
    private long bitrate;

    private long originBitrate;


    /**
     * 视频帧数
     */
    private double fps;
    private double originFps;

    /**
     * 视频格式
     */
    private String format;
    private String originFormat;


    private String convertInfo;


}
