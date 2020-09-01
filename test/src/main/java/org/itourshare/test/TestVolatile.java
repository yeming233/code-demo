package org.itourshare.test;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

	public static void main(String[] args) {
		Demo d=new Demo();// 线程操作资源类
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"\t come in："+Demo.num);
			try {
				TimeUnit.SECONDS.sleep(3);
			}catch (Exception e) {
				e.printStackTrace();
			}
			d.add();
			System.out.println(Thread.currentThread().getName()+"\t update num value："+Demo.num);
		},"线程1").start();
		while(Demo.num == 0)
		{
			// main线程一直等待循环，直到num值不再等于0.
		}
		System.out.println(Thread.currentThread().getName()+"\t over："+Demo.num);
	}
}

class Demo {
	static int num = 0;

	public void add()
	{
		num = 60;
	}
}