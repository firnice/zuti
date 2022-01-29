package com.firnice.zuti.service.impl;

import com.firnice.zuti.service.UserService;
import com.firnice.zuti.service.model.UserPO;
import com.github.benmanes.caffeine.cache.*;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class UserServiceImpl implements UserService, InitializingBean {

    private Map<Long, UserPO> userMap = Maps.newHashMap();

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(value = "user")
    public List list() {
        log.info("list query from db");
        return Arrays.asList(userMap.values().toArray());
    }

    @Override
    @Cacheable(value = "user", key = "'user'.concat(#id.toString())")
    public UserPO findUserById(Long id) {
        log.info("findUserById query from db , id : {}", id);
        return userMap.get(id);
    }

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id.toString())")
    public UserPO update(UserPO userPO) {
        log.info("update db, user: {}", userPO.toString());
        userMap.put(userPO.getId(), userPO);
        return null;
    }


    @Override
    public UserPO findUserById2(Long id) {
        log.info("findUserById2");
        return userCache.get(id);
    }
    LoadingCache<Long, UserPO> userCache;
    @Override
    public void afterPropertiesSet() throws Exception {
        userMap.put(1L, new UserPO(1L, "aaa", "666666"));
        userMap.put(2L, new UserPO(2L, "bbb", "666666"));
        userMap.put(3L, new UserPO(3L, "ccc", "666666"));

        userCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, UserPO>() {
                    @Nullable
                    @Override
                    public UserPO load(@NonNull Long key) throws Exception {
                        log.info("load");
                        return   userDao.findUserById(key);
                    }

                    @Nullable
                    @Override
                    public UserPO reload(@NonNull Long key, @NonNull UserPO oldValue) throws Exception {
                        log.info("reload");
                        return userDao.findUserById(key);
                    }

                });

    }

    private UserPO createExpensiveGraph(Long key) {
        log.info("createExpensiveGraph");
        return userDao.findUserById(key);
    }
}
