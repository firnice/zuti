package com.firnice.zuti.interfaces.controller;

import com.firnice.zuti.service.model.TestCachePO;
import com.firnice.zuti.service.TestCache;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {


    @GetMapping("/list")
    public List<TestCachePO> test() {
        List<TestCachePO> result = Lists.newArrayList();
        Map<String, List<TestCachePO>> contentLocalMap = TestCache.getContentLocalMap();

        List<TestCachePO> test = contentLocalMap.get("test");
        if(CollectionUtils.isNotEmpty(test)){
            List<TestCachePO> tmp  = test.subList(0, 10);

            tmp.stream().forEach(testCachePO -> {
                TestCachePO testCachePO1 = new TestCachePO();
                testCachePO1.setK0(testCachePO.getK0());
                testCachePO1.setK1(testCachePO.getK1());
                testCachePO1.setK2(testCachePO.getK2());
                testCachePO1.setK3(testCachePO.getK3());
                testCachePO1.setK4(testCachePO.getK4());
                testCachePO1.setK5(testCachePO.getK5());
                testCachePO1.setK6(testCachePO.getK6());
                testCachePO1.setK7(testCachePO.getK7());
                testCachePO1.setK8(testCachePO.getK8());
                testCachePO1.setK9(testCachePO.getK9());
                result.add(testCachePO1);
            });
        }
        return result;
    }
}
