package com.example.norma.Multithreading.ThreadClass;

import org.junit.jupiter.api.Test;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--04--16:28
 * @Description: 继承Thread类
 */
public class ThreadDemo1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class ThreadTest1 {
    @Test
    public static void main(String[] args) {
        ThreadDemo1 t1 = new ThreadDemo1();
        t1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程" + i);
        }
    }
}

/**
 * Thread()创建一个新线程。
 * Thread(String name)创建一个具有指定名称的新线程。
 * Thread(Runnable target)创建一个具有指定Runnable对象的新线程。
 * Thread(Runnable target,String name)创建一个具有指定名称和指定Runnable对象的新线程。
 * */
class ThreadWay extends  Thread{
    @Test
    public static void main(String[] args) {
        //
        Thread t1 = new Thread();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程运行…………");
            }
        });

        Thread t3 = new Thread("三号线程");

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程运行…………");
            }
        },"四号线程");
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadWay1 extends  Thread {
    @Test
    public static void main(String[] args) {
        Thread thread1 = new Thread("我是线程");
        thread1.start();
        // 打印线程信息
        System.out.println("线程名称：" + thread1.getName());
        System.out.println("线程ID：" + thread1.getId());
        System.out.println("线程状态：" + thread1.getState());
        System.out.println("线程优先级：" + thread1.getPriority());
        System.out.println("是否为守护线程：" + thread1.isDaemon());
        System.out.println("线程是否存活：" + thread1.isAlive());
        System.out.println("线程是否被中断：" + thread1.isInterrupted());
        System.out.println("----------------------------------------------");

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "我还存活");
                    System.out.println(Thread.currentThread().isAlive());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "我即将死去");
        },"111号线程~");

        thread.start();
        try {
            // 等待线程执行完毕
            thread.join();
        } catch (InterruptedException e) {
            // 打印异常信息
            e.printStackTrace();
        }
// 线程执行完毕，此时线程已不存在，因此 isAlive() 返回 false
        System.out.println(thread.isAlive());
    }

}

class MyThread  {
    @Test
    public static void main(String[] args) {
        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                System.out.println("thread end.");
            }
        };
        t.start();
        System.out.println("main end...");
    }
}

