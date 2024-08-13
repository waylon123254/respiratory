package com.example.norma.Multithreading.Priority;

import org.junit.jupiter.api.Test;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--04--16:40
 * @Description: 线程优先级
 * @version: 1.0
 */
public class PriorityTest {
    @Test
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    /**
                     * 线程优先级
                     * 1.优先级高的线程优先执行
                     * 2.优先级高的线程不一定先执行，取决于CPU的调度
                     * 3.优先级高的线程只是获得执行的机会，而非一定先执行
                     * 4.线程的优先级有1-10共10个等级，JVM默认为5
                     * 5.线程的优先级具有继承性，比如A线程启动了B线程，则B线程的优先级与A是一样的
                     * 6.线程的优先级具有规则性，优先级高的线程获得执行的机会多，但并不一定先执行
                     * 7.高优先级的线程抢占到CPU的概率高，但低优先级的线程不一定没有执行的机会
                     * 8.优先级低的线程不一定完全被忽略，优先级高的线程不一定完全先执行
                     * 9.线程的优先级具有随机性，优先级高的线程只是获得执行的机会，并不是一定先执行
                     * 10.线程的优先级具有传递性，比如A线程启动了B线程，则B线程的优先级与A是一样的
                     * 11.线程的优先级具有重量级性，优先级高的线程执行时间长，但并不一定先执行
                     * 12.线程的优先级具有非线性性，优先级高的线程执行时间不一定长
                     * 13.线程的优先级具有非均匀性，优先级高的线程不一定完全先执行
                     * 14.线程的优先级具有非独立性，优先级高的线程执行时间长，但并不一定先执行
                     * 15.线程的优先级具有非单调性，优先级高的线程不一定完全先执行
                     * 16.线程的优先级具有非唯一性，优先级高的线程不一定完全先执行
                    *   匿名内部类使用外部变量
                    *   外部变量必须为final或有效final
                    *   外部变量不能是引用类型
                    * */

                }
            });
            if (i % 2 == 0) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.MIN_PRIORITY); // 可以设置最低优先级作为对比
            }
            thread.start();
        }
    }
}
class Priority1{

}

