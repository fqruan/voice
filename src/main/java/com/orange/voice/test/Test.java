package com.orange.voice.test;

import com.orange.voice.util.SecurityUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        String s = "123A";
        System.out.println(SecurityUtil.getMD5(s));
    }

    public static void func() {
        AtomicInteger i = new AtomicInteger(2000000);
        funcAdd(i);
        funcAdd(i);
        System.out.println(i);
    }

    public static void funcAdd(AtomicInteger a) {
        a.getAndIncrement();
    }

}
