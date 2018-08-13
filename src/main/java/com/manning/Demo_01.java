package com.manning;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo_01 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> integers1 = integers.subList(0, 100);
        System.out.println(integers1);
    }
}

class RateLimiterDemo {

    //每秒发出5个令牌
    private static RateLimiter limiter = RateLimiter.create(5);

    //尝试获取令牌
    public boolean tryAcquire() {
        return limiter.tryAcquire();
    }

    public static void exec() {
        limiter.acquire(1);
        try {
            // 处理核心逻辑
            TimeUnit.SECONDS.sleep(1);
            System.out.println("--" + System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}