package com.example.norma.Multithreading.Way;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--10--16:18
 * @Description:
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + " " + i);
                }
            }
        });

        t1.start();
        t1.join();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + " " + i + "+++++++++++++++++++++++++");
                }
            }
        });
        t2.start();
        t2.join();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + " " + i);
                }
            }
        });

        t3.start();
        t3.join();

        System.out.println("主线程执行完毕");
        System.out.println("————————————————————————————");
        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程c1:" + Thread.currentThread().getName() + " " + i);
                }
            }
        });
        c1.start();

        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程c2:" + Thread.currentThread().getName() + " " + i);
                }
            }
        });
        c2.start();

        Thread c3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + " " + i);
                }
            }
        });
        c3.start();
        c3.join();


        Thread x1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000); // 假设线程需要等待一段时间
                    System.out.println("x1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        x1.start();
        x1.join(); // 等待 x1 线程执行完成

        Thread x2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("x2");
            }
        });
        x2.start();
        x2.join();
    }
}
