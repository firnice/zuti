package com.firnice.zuti.service;

import com.firnice.zuti.service.model.UserPO;

import java.util.List;

public interface UserService {


    List<UserPO> list();

    UserPO findUserById(Long id);

    UserPO update(UserPO user);
}
