package com.example.norma.Multithreading.RunnableClass;

import org.junit.jupiter.api.Test;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--04--16:36
 * @Description: 继承Runnable接口
 */
public class RunnableDemo1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class RunnableTest2 {
    @Test
    public static void main(String[] args) {
        RunnableDemo1 r1 = new RunnableDemo1();
        Thread t1 = new Thread(r1);
        t1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class RunnableTest3{
    @Test
    public static void main(String[] args) {
        Thread t2 =new Thread(new RunnableDemo1());
        t2.start();
    }
}

class RunnableTest4{
    @Test
    public static void main(String[] args) {
        Thread t3 =new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        });
        t3.start();
    }
}

/**
 * 优先级
 * */
class CustomThread extends Thread {
    //线程调度
    public CustomThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("父线程优先级：" + this.getPriority());
        Thread t = new Thread(new CustomRunnable());
        System.out.println("子线程优先级：" + t.getPriority());
    }
}

class CustomRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("CustomRunnable : " + i);
        }
    }
}



class CustomThreadTest {
    @Test
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        System.out.println("获取Thread线程优先级："+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println("获取线程优先级"+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(8);
        System.out.println(Thread.currentThread().getPriority());

//        Thread t = new CustomThread();
//        t.setPriority(3);
//        t.start();
        //线程调度的使用
        Thread t1 = new CustomThread("A");
        Thread t2 = new CustomThread("B");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}

