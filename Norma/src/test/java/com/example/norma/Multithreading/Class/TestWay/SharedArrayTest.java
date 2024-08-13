package com.example.norma.Multithreading.Class.TestWay;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--07--17:15
 * @Description:
 */
public class SharedArrayTest {
    private Runnable task = () -> {
        String threadName = Thread.currentThread().getName();
        System.out.println("Hello " + threadName);
    };

    public void startThread() {
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void main(String[] args) {
        SharedArrayTest test = new SharedArrayTest();
        test.startThread();
        System.out.println("Done!");

    }
}
 class TaskTest {
    private volatile boolean isDone = false;

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public interface Task {
        void execute();
    }

    private Task task;

    public TaskTest(Task task) {
        this.task = task;
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setDone(true);
                System.out.println("Thread " + Thread.currentThread().getName() + " is done");
            }
        }
    }

    public static void main(String[] args) {
        TaskTest taskTest = new TaskTest(new Task() {
            @Override
            public void execute() {
                // Task implementation goes here
            }
        });

        WorkerThread workerThread = taskTest.new WorkerThread();
        workerThread.start();
    }
}




