package com.example.norma.Multithreading.Way;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--10--15:27
 * @Description:
 */
public class YieldTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() { // 创建Thread对象
                @Override
                public void run() {
                    if (finalI % 2 == 0) { // 添加大括号以包围条件块
                        System.out.println(Thread.currentThread().getName() + "线程正在执行");
                        Thread.yield(); // 第一个yield调用
                        Thread.currentThread().yield(); // 第二个yield调用，与第一个yield调用相同
                    }
                    System.out.println("i=" + finalI); // 使用finalI来输出正确的值
                    System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                }
            });
            thread.start(); // 正确地启动线程
        }
    }
}
class YiledTest implements Runnable {
    private String name;

    public YiledTest(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + ":" + i);
            if ("t1".equals(name) && i == 5) {
                System.out.println(name + ":" + i + "......yield.............");
                Thread.yield();
            }
            if ("t2".equals(name) && i == 20) {
                System.out.println(name + ":" + i + "......yield.............");
                Thread.yield();
            }
        }
    }

    /**
     * 暂停当前正在执行的线程对象，并执行其他线程。  *  * @param args  * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new YiledTest("t1"));
        Thread t2 = new Thread(new YiledTest("t2"));
        t1.start();
        t2.start();
    }
}
