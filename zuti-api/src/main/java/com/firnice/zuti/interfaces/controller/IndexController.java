package com.firnice.zuti.interfaces.controller;


import com.firnice.zuti.service.UserService;
import com.firnice.zuti.service.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {


    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<UserPO> users() {
        return userService.list();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserPO findUserById(@PathVariable("id") Long id) {
//        return userService.findUserById(id);
        return userService.findUserById2(id);
    }


    @GetMapping("/user/{id}/{name}")
    @ResponseBody
    public Map update(@PathVariable("id") Long id, @PathVariable("name") String name) {
        UserPO user = userService.findUserById(id);
        user.setName(name);
        userService.update(user);
        Map<String, Object> result = new HashMap<>();
        result.put("ret", 0);
        result.put("msg", "ok");
        return result;
    }


}
