package com.example.norma.Multithreading.Class.TestWay;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--07--17:16
 * @Description: 线程结果不确定性
 */
public class TwoThreadTest {
    public static void main(String[] args) {
        new MyThread("1-1").start();
        new MyThread("1-2").start();

    }
}
class MyThread extends Thread {
    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("线程："+Thread.currentThread().getName()+"值："+i);
        }

    }
}
