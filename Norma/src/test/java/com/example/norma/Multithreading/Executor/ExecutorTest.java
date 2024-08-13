package com.example.norma.Multithreading.Executor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--07--11:02
 * @Description: 线程池创建
 */
public class ExecutorTest {
    @Test
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // 创建一个固定大小的线程池
        executorService.submit(() -> { // 提交任务到线程池中执行
            System.out.println("Task is running on thread " + Thread.currentThread().getName());
        });
        executorService.shutdown(); // 关闭线程池
    }
}

class Task implements Runnable {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // 执行任务逻辑
        if ("Task 1".equals(taskName)) {
            // 任务1的逻辑
            System.out.println("Task 1 is running on thread " + Thread.currentThread().getName());
        } else if ("Task 2".equals(taskName)) {
            // 任务2的逻辑
            System.out.println("Task 2 is running on thread " + Thread.currentThread().getName());
        } else if ("Task 3".equals(taskName)) {
            // 任务3的逻辑
            System.out.println("Task 3 is running on thread " + Thread.currentThread().getName());
        }
    }
}
class TaskTest{
    @Test
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3); // 创建一个固定大小的线程池，最多同时运行3个任务

        // 创建多个任务对象并提交给线程池执行
        executor.submit(new Task("Task 1"));
        executor.submit(new Task("Task 2"));
        executor.submit(new Task("Task 3"));

        // 关闭线程池，不再接受新的任务，但仍会执行已提交的任务
        executor.shutdown();
    }
}

