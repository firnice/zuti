package com.firnice.zuti.dynamicproxy.cglib;

import com.firnice.zuti.staticproxy.SmsService;
import com.firnice.zuti.staticproxy.SmsServiceImpl;

public class Main {

    public static void main(String[] args) {
        SmsService proxy = (SmsService) CglibProxyFactory.getProxy(SmsServiceImpl.class);
        proxy.send("cglib");

        System.out.println(proxy.hashCode());
    }
}
