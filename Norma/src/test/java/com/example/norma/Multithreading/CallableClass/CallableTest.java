package com.example.norma.Multithreading.CallableClass;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: 吕宏博
 * @Date: 2024--08--12--11:26
 * @Description:
 */
public class CallableTest  {
    public static void main(String[] args) {
        Callable<String> stringCallable = new Callable<String>(){
            @Override
            public String call() throws Exception {
                return "Callable线程创建";
            }
        };
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 提交Callable任务
        Future<String> future = executorService.submit(stringCallable);

        // 获取Callable任务的返回值
        try {
            String result = future.get();
            System.out.println("Callable线程执行结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executorService.shutdown();
    }


}

