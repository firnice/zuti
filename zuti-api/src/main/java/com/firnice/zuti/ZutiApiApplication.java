package com.firnice.zuti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.firnice")
@EnableScheduling
public class ZutiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZutiApiApplication.class, args);
    }

}
