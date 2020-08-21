package com.firnice.zuti.dynamicproxy.jdk;

import com.firnice.zuti.staticproxy.SmsService;
import com.firnice.zuti.staticproxy.SmsServiceImpl;

public class Main {

    public static void main(String[] args) {

        SmsServiceImpl smsService = new SmsServiceImpl();
        SmsService sms = (SmsService) JdkProxyFactory.getProxy(smsService);
        sms.send("java");
    }
}
