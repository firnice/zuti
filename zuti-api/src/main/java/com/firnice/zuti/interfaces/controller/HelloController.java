package com.firnice.zuti.interfaces.controller;

import com.firnice.zuti.model.TestCachePO;
import com.firnice.zuti.service.TestCache;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {


    @GetMapping
    public List<TestCachePO> test() {
        List<TestCachePO> result = Lists.newArrayList();
        Map<String, List<TestCachePO>> contentLocalMap = TestCache.getContentLocalMap();

        List<TestCachePO> test = contentLocalMap.get("test");
        if(CollectionUtils.isNotEmpty(test)){
            result = test.subList(0, 10);
        }

        return result;
    }
}
