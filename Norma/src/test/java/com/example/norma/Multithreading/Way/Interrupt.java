package com.example.norma.Multithreading.Way;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--12--10:41
 * @Description:
 */
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(5); // 暂停1毫秒
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end");
    }
}

class MyThread extends Thread {
    public void run() {
        int n = 0;
        while (! Thread.currentThread().isInterrupted()) { // 使用Thread.currentThread().isInterrupted()来检查当前线程是否被中断
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("Thread interrupted");
    }
}

class InterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is running..."); // 线程开始运行
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                System.out.println("After for loop");
                try {
                    Thread.sleep(1000); // 暂停1000毫秒
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted during sleep");
                    Thread.currentThread().interrupt(); // 重新设置中断标志
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread is interrupted after sleep");
                }
                System.out.println("After catch block");
            }
        });
        thread.start(); // 启动线程
        try {
            Thread.sleep(100); // 暂停1毫秒
        } catch (InterruptedException e) {
            System.out.println("Main thread is interrupted");
        }
        thread.interrupt(); // 中断线程
    }
}

class MyInterruptThread{
    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("进入线程内部，开始等待");
                }
                    try {
                        lock.wait(); // 等待唤醒
                    }
                    catch (InterruptedException e) {

                        System.out.println("线程在等待时被中断");
                        Thread.currentThread().interrupt(); // 重新设置中断标志
                    }
                System.out.println("等待结束");
            }
        });
        thread.start(); // 启动线程
        // 主线程中断等待线程
        thread.interrupt();
    }
}
