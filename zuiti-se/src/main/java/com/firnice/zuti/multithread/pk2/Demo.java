package com.firnice.zuti.multithread.pk2;

import java.security.*;

public class Demo {

    public static void main(String args[]) throws Exception {
        // Turn on the security management
        SecurityManager sm = new SecurityManager();
        System.setSecurityManager(sm);

        new Thread(new SafeRunnable() {
            @Override
            public void protectedRun() {
                // friendly operation:
                System.out.println("Hello");
            }
        }).start();

        new Thread(new SafeRunnable() {
            @Override
            public void protectedRun() {
                // malicious operation
                System.exit(0);
            }
        }).start();
    }



}

