package com.firnice.zuti.service;


import com.firnice.zuti.model.TestCachePO;
import com.firnice.zuti.util.StringUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
@Slf4j
public class TestCache {

    private static final Map<String, List<TestCachePO>> contentLocalMap = new HashMap<>();


    /**
     * 刷新本地缓存
     *
     */
    public void cacheFlush() {
        log.info("localCache begin");
        try {
            List<TestCachePO> list = Lists.newArrayList();
            for (int i = 0; i < 20000; i++) {
                TestCachePO testCachePO = new TestCachePO();
                testCachePO.setK0(StringUtil.getRandomString(50));
                testCachePO.setK1(StringUtil.getRandomString(50));
                testCachePO.setK2(StringUtil.getRandomString(50));
                testCachePO.setK3(StringUtil.getRandomString(50));
                testCachePO.setK4(StringUtil.getRandomString(50));
                testCachePO.setK5(StringUtil.getRandomString(50));
                testCachePO.setK6(StringUtil.getRandomString(50));
                testCachePO.setK7(StringUtil.getRandomString(50));
                testCachePO.setK8(StringUtil.getRandomString(50));
                testCachePO.setK9(StringUtil.getRandomString(50));
                list.add(testCachePO);
            }
            contentLocalMap.put("test",list);
        } catch (Exception e) {
            log.info("cc初始化本地内容出错了",e);
        }
        log.info("localCache end");
    }

}
