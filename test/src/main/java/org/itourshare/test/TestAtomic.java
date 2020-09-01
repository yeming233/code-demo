package org.itourshare.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic implements Runnable{
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    private static volatile int basicCount = 0;
 
    public void invrementAtomic(){
        atomicInteger.getAndIncrement();
    }
 
    public void incrementBasic(){
        basicCount ++ ;
    }
 
 
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            invrementAtomic();
            incrementBasic();
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
        TestAtomic demo = new TestAtomic();
        Thread th1 = new Thread(demo);
        Thread th2 = new Thread(demo);
        Thread th3 = new Thread(demo);
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        System.out.println("原子类的结果是"+atomicInteger.get());
        System.out.println(basicCount);
    }
}