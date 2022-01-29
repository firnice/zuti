package com.firnice.api;

import com.firnice.zuti.MsApiApplication;
import com.firnice.zuti.model.ConvertInfoV2Vo;
import com.firnice.zuti.model.TransferToolsEnum;
import com.firnice.zuti.model.VideoTransferV2Vo;
import com.firnice.zuti.service.ConvertInfoV2VoFactory;
import com.firnice.zuti.service.JavaCVTransferV2ServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MsApiApplication.class)
public class Test {

    @Autowired
    private JavaCVTransferV2ServiceImpl javaCVTransferV2Service;

    @org.junit.Test
    public void fun() throws Exception {
        VideoTransferV2Vo videoTransferV2Vo = new VideoTransferV2Vo();
        ConvertInfoV2Vo convertInfoV2Vo = ConvertInfoV2VoFactory.get720P();
        videoTransferV2Vo.setBusinessCode("12117");
        videoTransferV2Vo.setCallbackUrl("http://localhost:8094//ms/api/video/test");
        videoTransferV2Vo.setVideoUrl("/Users/firnice/Downloads/1.mov");
        videoTransferV2Vo.setDestFilename(System.currentTimeMillis()+"_media_test.mp4");
        videoTransferV2Vo.setDestPath("cc/mstemp/");
        videoTransferV2Vo.setConvertInfoV2Vo(convertInfoV2Vo);
        videoTransferV2Vo.setTransferTool(TransferToolsEnum.JAVACV);
        javaCVTransferV2Service.formatConversion(videoTransferV2Vo);

    }
}
