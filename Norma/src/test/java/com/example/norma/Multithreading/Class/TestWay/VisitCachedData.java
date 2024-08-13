package com.example.norma.Multithreading.Class.TestWay;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--07--17:24
 * @Description:
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 主类，程序的入口
public class VisitCachedData {
    /**
     * 它的主要作用是初始化一个共享的任务对象 Task，创建一个固定大小的线程池，并向线程池提交两个 WorkTask 实例。
     *
     * 需要注意的是，这里使用了一个共享的任务对象 Task，用于在多个线程间共享数据。
     *
     * 程序启动两个线程来并行执行任务，并通过调用 shutdown 和 awaitTermination 方法来确保所有任务完成后程序才退出
     * */
    public static void main(String[] args) {
        // 创建一个任务对象，用于在多个线程间共享状态
        Task task = new Task();
        // 创建一个固定大小的线程池，大小为2
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 提交两个任务到线程池
        executor.execute(new WorkTask(task));
        executor.execute(new WorkTask(task));
        // 关闭线程池，不再接受新任务
        executor.shutdown();
        try {
            // 等待所有任务完成，或超时（这里设置为无限等待）
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // 打印异常堆栈信息
            e.printStackTrace();
        }
    }
}
/**
 * 这个类用于表示一个可以被多个线程共享的任务状态。
 * 它包含一个 volatile 类型的布尔变量 isDone，该变量用于标记任务是否完成。
 * Task 类提供了 isDone 和 setDone 方法，用于获取和设置任务的完成状态。setDone 方法是同步的，确保在多线程环境下对 isDone 变量的修改是线程安全的。
 * */
// 任务类，用于在多个线程间共享状态
class Task {
    // 声明一个volatile变量，确保其可见性
    private volatile boolean isDone = false;

    // 获取任务是否完成的状态
    public boolean isDone() {
        return isDone;
    }

    // 设置任务完成状态，同步方法确保线程安全
    public synchronized void setDone(boolean done) {
        isDone = done;
    }
}

// 工作任务类，实现了Runnable接口，可以被线程执行
class WorkTask implements Runnable {
    // 任务对象，用于在多个工作线程之间共享
    private Task currentTask;

    // 构造方法，接收一个任务对象
    public WorkTask(Task task) {
        currentTask = task;
    }

    // 实现的run方法，线程启动时会调用此方法
    @Override
    public void run() {
        // 循环10次，模拟执行任务
        for (int i = 0; i < 10; i++) {
            // 输出当前任务编号和执行任务的线程ID
            System.out.println("任务编号: " + i + " 正在被线程: " + Thread.currentThread().getId() + " 处理");
            try {
                // 模拟任务执行需要一定时间，线程休眠100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 打印异常堆栈信息
                e.printStackTrace();
            }
            // 设置任务完成状态为true
            currentTask.setDone(true);
        }
    }
}

