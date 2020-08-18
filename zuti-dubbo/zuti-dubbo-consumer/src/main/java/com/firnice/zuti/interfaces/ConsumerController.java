package com.firnice.zuti.interfaces;

import com.firnice.zuti.interfaces.dto.ProviderTestDTO;
import com.firnice.zuti.interfaces.vo.ResultVO;
import com.firnice.zuti.service.IProviderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer")

public class ConsumerController {

    // Dubbo远程调用注解
    @Reference
    private IProviderService providerService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO getList(){
        // 远程调用
        List<ProviderTestDTO> providerTestDTOList = providerService.queryList();
        return new ResultVO.Builder<>().code(200).message("success").data(providerTestDTOList).build();
    }
}
