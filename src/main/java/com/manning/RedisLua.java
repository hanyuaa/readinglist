package com.manning;

import org.apache.commons.io.FileUtils;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hanyu on 2018/7/20.
 */
public class RedisLua {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 7; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                        System.out.println(accquire());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        latch.countDown();
    }

    public static boolean accquire() throws IOException, URISyntaxException {
        Jedis jedis = new Jedis("127.0.0.1");
        File luaFile = new File(RedisLua.class.getResource("/").toURI().getPath() + "limit.lua");
        String luaScript = FileUtils.readFileToString(luaFile);

        String key = "ip:" + System.currentTimeMillis()/1000; // 当前秒
        String limit = "5"; // 最大限制
        List<String> keys = new ArrayList<String>();
        keys.add(key);
        List<String> args = new ArrayList<String>();
        args.add(limit);
        Long result = (Long)(jedis.eval(luaScript, keys, args)); // 执行lua脚本，传入参数
        return result == 1;
    }
}
