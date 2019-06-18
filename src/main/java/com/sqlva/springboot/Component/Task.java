package com.sqlva.springboot.Component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Task {
    @Async
    public Future<String> doTaskOne() throws Exception {
//        // 同上内容，省略
//        System.out.println("开始做任务一");
//        long start = System.currentTimeMillis();
//        Thread.sleep(random.nextInt(10000));
//        long end = System.currentTimeMillis();
//        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String>  doTaskTwo() throws Exception {
        // 同上内容，省略
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String>  doTaskThree() throws Exception {
        // 同上内容，省略
        return new AsyncResult<>("任务三完成");
    }

}
