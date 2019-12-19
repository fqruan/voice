package com.orange.voice.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        func();
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
