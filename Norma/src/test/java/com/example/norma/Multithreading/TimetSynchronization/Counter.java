package com.example.norma.Multithreading.TimetSynchronization;

import lombok.var;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--09--15:31
 * @Description:
 */
public class Counter {
    // 使用当前对象实例作为锁
    // 这样可以确保同一时间只有一个线程能够进入synchronized代码块
    // 这里的this关键字代表当前Counter实例
    // 这样可以保证操作的线程安全性
    private int count = 0;

    // 增加计数的方法
    public void add(int n) {
        // 进入synchronized代码块，需要获得当前对象实例的锁
        synchronized(this) {
            count += n;
        }
        System.out.println("添加了 " + n + ", 计数器现在是 " + count);
    }

    // 减少计数的方法
    public void dec(int n) {
        // 进入synchronized代码块，需要获得当前对象实例的锁
        synchronized(this) {
            count -= n;
        }
        System.out.println("减少了 " + n + ", 计数器现在是 " + count);
    }

    // 获取当前计数的方法
    public int get() {
        System.out.println("当前计数是 " + count);
        return count;
    }
}

class UnsafeCounter {
    private int count = 0;

    public void increment(int count) {
        count++;
        System.out.println("增加了 " + count + ", 计数器现在是 " + count);
    }

    public void decrement(int count) {
        count--;
        System.out.println("减少了 " + count + ", 计数器现在是 " + count);
    }

    public int getCount() {
        return count;
    }
}

class CounterTest {
    public static void main(String[] args) {
        // 使用Counter类实例
        var c1 = new Counter();
        var c2 = new Counter();

        // 对c1进行操作
        c1.add(1);
        c1.dec(1);

        // 对c2进行操作
        c2.add(1);
        c2.dec(1);

        // 输出最终的计数
        System.out.println("c1:"+"c1的最终计数是 " + c1.get());
        System.out.println("c2的最终计数是 " + c2.get());
        System.out.println("_------------------------------_");

        // 使用UnsafeCounter类实例
        var c3 = new UnsafeCounter();
        var c4 = new UnsafeCounter();

        // 对c3进行操作
        c3.increment(1);
        c3.decrement(1);

        // 对c4进行操作
        c4.increment(1);
        c4.decrement(1);

        // 输出最终的计数
        System.out.println("c3的最终计数是 " + c3.getCount());
        System.out.println("c4的最终计数是 " + c4.getCount());
    }
}
