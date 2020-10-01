package org.itourshare.test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : TestReentrant
 * @Description :
 * @Author : its
 * @Date: 2020-10-01 10:36
 */
public class TestReentrant {

    public static void main(String[] args) {
        testReentrantLock();
    }

    static void testSynchronized(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("第1次获取锁，这个锁是：" + this);
                    int index = 1;
                    while (true) {
                        synchronized (this) {
                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + this);
                        }
                        if (index == 10) {
                            break;
                        }
                    }
                }
            }
        }).start();
    }


    static void testReentrantLock(){
        ReentrantLock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("第1次获取锁，这个锁是：" + lock);

                    int index = 1;
                    while (true) {
                        try {
                            lock.lock();
                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + lock);

                            try {
                                Thread.sleep(new Random().nextInt(200));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (index == 10) {
                                break;
                            }
                        } finally {
                            lock.unlock();
                        }

                    }

                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}
