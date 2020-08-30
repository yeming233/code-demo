package org.itourshare.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : TestLock
 * @Description :
 * @Author : its
 * @Date: 2020-08-30 10:31
 */
public class TestLock {

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    volatile static int num = 0;

    static Object object = new Object();

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    incReentrantlock();
                }
            });
        }
    }

    synchronized static void incSync() {
        num++;
        System.out.println("===thread" + Thread.currentThread().getName() + "===num" + num);
    }


    static void incSyncBlock() {
        synchronized (object) {
            num++;
            System.out.println("===thread" + Thread.currentThread().getName() + "===num" + num);
        }
    }

    static void incReentrantlock() {
        lock.lock();
        try {
            num++;
            System.out.println("===thread" + Thread.currentThread().getName() + "===num" + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void inc() {
        num++;
        System.out.println("===thread" + Thread.currentThread().getName() + "===num" + num);
    }
}
