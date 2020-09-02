package org.itourshare.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : Test
 * @Description :
 * @Author : its
 * @Date: 2020-09-02 16:46
 */
public class TestThread2 {
    private static Lock lock = new ReentrantLock(true);
    private static int state = 0;

    public static void main(String[] args) {
        new ThreadA("threa A").start();
        new ThreadB("threa B").start();
    }


    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "===state" + state);
                    while (state % 2 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println(Thread.currentThread().getName() + "===" + "偶数");
                        state++;
                        i++;
                    }
                } finally {
                    System.out.println(Thread.currentThread().getName() + "===unlock");
                    lock.unlock();// unlock()操作必须放在finally块中

                }
            }
        }
    }

    static class ThreadB extends Thread {
        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "===state" + state);
                    while (state % 2 == 1) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.println(Thread.currentThread().getName() + "===" + "奇数");
                        state++;
                        i++;
                    }
                } finally {
                    System.out.println(Thread.currentThread().getName() + "===unlock");
                    lock.unlock();// unlock()操作必须放在finally块中

                }
            }
        }
    }

}
