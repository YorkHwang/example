package com.rosefinch.example.future;

import java.util.concurrent.TimeUnit;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThreadInter {

    public static void main(String[] args) {
        System.out.println("start:=====================");
        Thread.currentThread().interrupt();
        System.out.println("end:=====================");

        try {
            TimeUnit.SECONDS.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("interrupt:=====================");
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
