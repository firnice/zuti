package com.firnice.zuti.service;


import com.firnice.zuti.model.BitRateProcessingModeEnum;
import com.firnice.zuti.model.ConvertInfoV2Vo;
import com.firnice.zuti.model.ResolutionEnum;

/**
 * 提供转化的基础模板
 */
public class ConvertInfoV2VoFactory {

    /**
     * 普通的
     *
     * @return
     */
    public static ConvertInfoV2Vo getNormal() {
        ConvertInfoV2Vo convertInfoV2Vo = new ConvertInfoV2Vo();
        convertInfoV2Vo.setTargetSizeLimit(false);
        convertInfoV2Vo.setBitRateProcessingMode(BitRateProcessingModeEnum.MOVE_FACTORS);
        convertInfoV2Vo.setMoveFactors(1.5);
        convertInfoV2Vo.setTargetFpsLimit(true);
        convertInfoV2Vo.setTargetFps(25);
        convertInfoV2Vo.setResolution(ResolutionEnum.R_480P);
        return convertInfoV2Vo;

    }

    public static ConvertInfoV2Vo get720P() {
        ConvertInfoV2Vo convertInfoV2Vo = new ConvertInfoV2Vo();
        convertInfoV2Vo.setTargetSizeLimit(false);
        convertInfoV2Vo.setBitRateProcessingMode(BitRateProcessingModeEnum.MOVE_FACTORS);
        convertInfoV2Vo.setMoveFactors(1.5);
        convertInfoV2Vo.setTargetFpsLimit(true);
        convertInfoV2Vo.setTargetFps(30);
        convertInfoV2Vo.setResolution(ResolutionEnum.R_720P);
        return convertInfoV2Vo;

    }

    public static ConvertInfoV2Vo get1080P() {
        ConvertInfoV2Vo convertInfoV2Vo = new ConvertInfoV2Vo();
        convertInfoV2Vo.setTargetSizeLimit(false);
        convertInfoV2Vo.setBitRateProcessingMode(BitRateProcessingModeEnum.MOVE_FACTORS);
        convertInfoV2Vo.setMoveFactors(1.5);
        convertInfoV2Vo.setTargetFpsLimit(true);
        convertInfoV2Vo.setTargetFps(30);
        convertInfoV2Vo.setResolution(ResolutionEnum.R_1080P);
        return convertInfoV2Vo;

    }

    public static ConvertInfoV2Vo get60FpsAndHD() {
        ConvertInfoV2Vo convertInfoV2Vo = new ConvertInfoV2Vo();
        convertInfoV2Vo.setTargetSizeLimit(false);
        convertInfoV2Vo.setBitRateProcessingMode(BitRateProcessingModeEnum.MOVE_FACTORS);
        convertInfoV2Vo.setMoveFactors(4);
        convertInfoV2Vo.setTargetFpsLimit(false);
        convertInfoV2Vo.setResolution(ResolutionEnum.R_720P);
        return convertInfoV2Vo;

    }
}
