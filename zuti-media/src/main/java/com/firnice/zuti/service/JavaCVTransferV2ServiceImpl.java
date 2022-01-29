package com.firnice.zuti.service;

import com.firnice.zuti.model.*;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JavaCVTransferV2ServiceImpl   {




    public void formatConversion(VideoTransferV2Vo videoTransferV2Vo) throws Exception {
        log.info(">>>>> javacv formatConversion start! videoTransferV2Vo:{}", videoTransferV2Vo);

        VideoInfoV2 videoInfoV2 = new VideoInfoV2();
        FFmpegFrameGrabber frameGrabber = null;
        FFmpegFrameGrabber videoGrabber = null;
        FFmpegFrameRecorder recorder = null;
        FFmpegFrameFilter fFmpegFrameFilter = null;
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            frameGrabber = new FFmpegFrameGrabber(videoTransferV2Vo.getVideoUrl());
            log.info(">>>>>javacv step:1 file:{} get video info  ", videoTransferV2Vo.getDestFilename());

            // 启动FFmpegFrameGrabber
            frameGrabber.start();

            //原始视频的fps
            fFmpegFrameFilter = new FFmpegFrameFilter("fps=fps=" + frameGrabber.getFrameRate(), "anull", frameGrabber.getImageWidth(), frameGrabber.getImageHeight(), frameGrabber.getAudioChannels());
            fFmpegFrameFilter.setSampleFormat(frameGrabber.getSampleFormat());
            fFmpegFrameFilter.setSampleRate(frameGrabber.getSampleRate());
            fFmpegFrameFilter.setPixelFormat(frameGrabber.getPixelFormat());
            fFmpegFrameFilter.setFrameRate(frameGrabber.getFrameRate());
            fFmpegFrameFilter.start();

            // 获取原视频宽高
            int height = frameGrabber.getImageHeight();
            int width = frameGrabber.getImageWidth();
            videoInfoV2.setOriginMediaHeight(height);
            videoInfoV2.setOriginMediaWidth(width);

            // 获取原视频时长
            double duration = frameGrabber.getLengthInTime() * 1.0 / (1000 * 1000);
            videoInfoV2.setOriginDuration(duration);

            // 获取原视频大小
            int ab = frameGrabber.getAudioBitrate() / 1000;
            int vb = frameGrabber.getVideoBitrate() / 1000;
            long size = (ab + vb) * 1024 * new BigDecimal(duration).longValue() / 8;
            videoInfoV2.setOriginMediaSize(size);

            //原始fps
            double fps = frameGrabber.getFrameRate();
            videoInfoV2.setOriginFps(fps);
            videoInfoV2.setOriginBitrate(frameGrabber.getVideoBitrate());
            videoInfoV2.setOriginFormat(String.valueOf(frameGrabber.getVideoCodec()));


            ConvertInfoV2Vo convertInfoV2Vo = videoTransferV2Vo.getConvertInfoV2Vo();

            //设置分辨率，最小边
            log.info(">>>>>step:2 file:{} set resolution :{}", videoTransferV2Vo.getDestFilename(),convertInfoV2Vo.getResolution());
            if (!convertInfoV2Vo.getResolution().equals(ResolutionEnum.R_DEFAULT)) {
                int minLength = convertInfoV2Vo.getResolution().getCode();
                if (Math.min(width, height) > minLength) {
                    if (width < height) {
                        height = (minLength * height) / width;
                        if (height % 2 != 0) {
                            height++;
                        }
                        width = minLength;
                    } else {
                        width = (minLength * width) / height;
                        if (width % 2 != 0) {
                            width++;
                        }
                        height = minLength;
                    }
                }else {
                    log.info(">>>>>step:2.1 file:{} not have to set resolution origin_width:{} origin_height:{}", videoTransferV2Vo.getDestFilename(), width, height);
                }
            }


            log.info(">>>>>step:3 file:{} get video fps, origin_fps:{} ", videoTransferV2Vo.getDestFilename(), fps);

            double setFps = 0;
            //fps设置
            if (convertInfoV2Vo.isTargetFpsLimit()) {
                log.info(">>>>>step:3.1 file:{} fps{}, target_fps:{} ", videoTransferV2Vo.getDestFilename(), fps,convertInfoV2Vo.getTargetFps());
                //只有大于设置的才替换
                if (fps > convertInfoV2Vo.getTargetFps()) {
                    setFps = convertInfoV2Vo.getTargetFps();
                }
            }


            log.info(">>>>>step:4 file:{} set save file ", videoTransferV2Vo.getDestFilename());

            // 本地存储文件路径
            String localOutputDir = "/Users/firnice/Downloads/out/";
            File file = new File(localOutputDir);
            if (!file.exists()) {
                // 如果本地目录不存在，则创建
                file.mkdirs();
            }
            String localOutputFile = localOutputDir + videoTransferV2Vo.getDestFilename();

            // 创建FFmpegFrameRecorder对象
            log.info(">>>>>step:5.1 file:{} create FFmpegFrameRecorder width:{} height:{} ", videoTransferV2Vo.getDestFilename(), width,height);
            recorder = new FFmpegFrameRecorder(localOutputFile, width, height, frameGrabber.getAudioChannels());

            /**
             *优先设置大小，根据大小自动算出码率
             */
            if (convertInfoV2Vo.getBitRateProcessingMode().equals(BitRateProcessingModeEnum.USER_SETTING)) {
                /**
                 * 固定码率
                 */
                log.info(">>>>>step:5.2 file:{} set USER_SETTING: origin_bit_rate:{} set_bit_rate:{}", videoTransferV2Vo.getDestFilename(), frameGrabber.getVideoBitrate(), convertInfoV2Vo.getBitRate());
                if (frameGrabber.getVideoBitrate() > convertInfoV2Vo.getBitRate()) {
                    recorder.setVideoBitrate(convertInfoV2Vo.getBitRate());
                }
            } else if (convertInfoV2Vo.getBitRateProcessingMode().equals(BitRateProcessingModeEnum.MOVE_FACTORS)) {
                /**
                 * 根据分辨率、fps、运动因子算出码率
                 * 查看一个资料，说均衡考虑建议设为videoWidth*videoHeight*frameRate*0.07*运动因子，运动因子则与视频中画面活动频繁程度有关，如果很频繁就设为4，不频繁则设为1
                 */
                int videoBitRate = (int) ((width * height * fps) * convertInfoV2Vo.getMoveFactors() * 0.07);
                log.info(">>>>>step:5.2 file:{} set MOVE_FACTORS:{} origin_bit_rate:{} set_bit_rate:{}", videoTransferV2Vo.getDestFilename(), convertInfoV2Vo.getMoveFactors(), frameGrabber.getVideoBitrate(), videoBitRate);
                if (frameGrabber.getVideoBitrate() > videoBitRate) {
                    recorder.setVideoBitrate(videoBitRate);
                }else {
                    recorder.setVideoBitrate(frameGrabber.getVideoBitrate());
                }
            }

            /**
             * 设置fps
             */
            if (convertInfoV2Vo.isTargetFpsLimit() && setFps > 0) {
                log.info(">>>>>step:5.3 file:{} origin_fps:{} set fps:{}", videoTransferV2Vo.getDestFilename(), fps, setFps);
                recorder.setFrameRate(setFps);
            }else {
                recorder.setFrameRate(frameGrabber.getFrameRate());
            }
            recorder.setSampleRate(frameGrabber.getSampleRate());
            recorder.setAudioChannels(frameGrabber.getAudioChannels());
            recorder.setVideoOption("preset", "faster");
            recorder.setVideoOption("strict", "experimental");
            recorder.setOption("movflags", " faststart");
            // yuv420p,像素
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.setFormat("mp4");
            //视频旋转
            if(frameGrabber.getVideoMetadata().containsKey("rotate")){
                recorder.setVideoMetadata("rotate",frameGrabber.getVideoMetadata("rotate"));
            }

            recorder.start();

            Frame capturedFrame;
            Frame pullFrame;
            while (true) {
                try {
                    capturedFrame = frameGrabber.grab();
                    if (capturedFrame == null) {
                        log.info("!!! end cvQueryFrame..video:{}", videoTransferV2Vo.getVideoUrl());
                        break;
                    }

                    if (capturedFrame.image != null || capturedFrame.samples != null) {
                        fFmpegFrameFilter.push(capturedFrame);
                    }
                    if ((pullFrame = fFmpegFrameFilter.pull()) != null) {
                        if (pullFrame.image != null || pullFrame.samples != null) {
                            recorder.record(pullFrame);
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                }
            }


            // FFmpegFrameGrabber停止，FFmpegFrameRecorder停止和释放
            recorder.stop();
            recorder.release();
            frameGrabber.stop();
            fFmpegFrameFilter.stop();



            // 获取新视频的大小
            videoGrabber = new FFmpegFrameGrabber(localOutputFile);
            // FFmpegFrameGrabber启动
            videoGrabber.start();
            duration = videoGrabber.getLengthInTime() * 1.0 / (1000 * 1000);
            ab = videoGrabber.getAudioBitrate() / 1000;
            vb = videoGrabber.getVideoBitrate() / 1000;
            size = (ab + vb) * 1024 * new BigDecimal(duration).longValue() / 8;
            videoInfoV2.setMediaSize(size);
            // 获取转换后视频宽高
            videoInfoV2.setMediaWidth(videoGrabber.getImageWidth());
            videoInfoV2.setMediaHeight(videoGrabber.getImageHeight());
            videoInfoV2.setDuration(videoGrabber.getLengthInTime() * 1.0 / (1000 * 1000));
            videoInfoV2.setBitrate(videoGrabber.getVideoBitrate());
            videoInfoV2.setFps(videoGrabber.getFrameRate());
            videoInfoV2.setFormat(String.valueOf(videoGrabber.getVideoCodec()));
            videoInfoV2.setOssName(videoTransferV2Vo.getDestFilename());
//            videoInfoV2.setConvertInfo(JSON.toJSONString(convertInfoV2Vo));


        } catch (Exception e) {
            log.error("视频转换异常:{}", e);
            throw e;
        } finally {
            // FFmpegFrameGrabber停止
            if (frameGrabber != null) {
                frameGrabber.stop();
            }
            // FFmpegFrameRecorder停止和释放
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
            // FFmpegFrameGrabber停止
            if (videoGrabber != null) {
                videoGrabber.stop();
            }
            if (fFmpegFrameFilter != null) {
                fFmpegFrameFilter.stop();
            }
        }
        System.out.println("end");
//        return videoInfoV2;
    }

}
