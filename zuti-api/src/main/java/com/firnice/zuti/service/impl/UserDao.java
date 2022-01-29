package com.firnice.zuti.service.impl;

import com.firnice.zuti.service.model.UserPO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
@Slf4j
public class UserDao implements InitializingBean {

    private Map<Long, UserPO> userMap = Maps.newHashMap();


    public UserPO findUserById(Long id) {
        String s = String.valueOf(new Random().nextInt(100));
        log.info("UserDao findUserById query from db , id : {} ,s:{}", id,s);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserPO userPO = userMap.get(id);
        userPO.setPassword(s);
        return userPO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userMap.put(1L, new UserPO(1L, "aaa", "666666"));
        userMap.put(2L, new UserPO(2L, "bbb", "666666"));
        userMap.put(3L, new UserPO(3L, "ccc", "666666"));
    }
}
