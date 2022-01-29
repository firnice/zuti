package com.firnice.zuti.model;

import lombok.Data;

@Data
public class VideoTransferV2Vo {

    /**
     * 视频url全路径
     */
    private String videoUrl;

    /**
     * 目标文件路径
     */
    private String destPath;

    /**
     * 目标文件名称
     */
    private String destFilename;

    /**
     * 业务唯一标识
     */
    private String businessCode;

    /**
     * 回调接口
     */
    private String callbackUrl;


    /**
     * 视频转换参数对象
     */
    private ConvertInfoV2Vo convertInfoV2Vo;

    /**
     * 转换使用的框架
     */
    private TransferToolsEnum transferTool;


    @Override
    public String toString() {
        return "VideoTransferVo{" +
                ", videoUrl='" + videoUrl + '\'' +
                ", destPath='" + destPath + '\'' +
                ", destFilename='" + destFilename + '\'' +
//                ", targetSizeLimit=" + targetSizeLimit +
                '}';
    }
}
