//package com.firnice.zuti.interfaces.provider;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@Api(value = "视频处理api", description = "视频处理api")
//@CrossOrigin(allowCredentials = "true")
//@Slf4j
//@RequestMapping("/video")
//public class MediaContorller {
//
//    /**
//     * 转化
//     */
//    @GetMapping(value = "/t")
//    @ResponseBody
//    @ApiOperation(value = "视频转化同步", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "videoPath", value = "源视频路径", dataType = "String", paramType = "query", required = true),
//            @ApiImplicitParam(name = "bitRate", value = "比特率", dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "targetSize", value = "目标视频大小", dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "frameRate", value = "帧率", dataType = "double", paramType = "query"),
//            @ApiImplicitParam(name = "filter", value = "过滤器",dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "styleType", value = "样式类型",dataType = "String", paramType = "query")
//
//    })
//    public ResponseView<Media> transferFromUrl(@RequestParam String videoPath,
//                                               @RequestParam(defaultValue = "1000000", required = false) long bitRate,
//                                               @RequestParam(defaultValue = "3145728", required = false) long targetSize,
//                                               @RequestParam(defaultValue = "25", required = false) double frameRate,
//                                               @RequestParam(defaultValue = "", required = false) String filter,
//                                               @RequestParam(defaultValue = "", required = false) String styleType,
//                                               HttpServletRequest request
//    ) {
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("ossPath",videoPath);
//        jsonObject.put("bitRate",bitRate);
//        jsonObject.put("targetSize",targetSize);
//        jsonObject.put("frameRate",frameRate);
//        jsonObject.put("filter",filter);
//        jsonObject.put("styleType",styleType);
//        RequestVo requestVo = new RequestVo(request.getHeader(Constant.PARAM_PROCESS_ID),jsonObject.toJSONString(),true);
//
//        ConvertInfo convertInfo = new ConvertInfo(bitRate, targetSize, frameRate, filter, styleType);
//        //转化
//        return brampTransferService.transfer(videoPath, convertInfo,requestVo);
//    }
//}
