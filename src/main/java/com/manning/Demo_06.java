package com.manning;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hanyu on 2018/4/30.
 */
@Component
public class Demo_06 {
    public static void main(String[] args) throws Exception {
       Reader reader = new Reader();
        String fullname = reader.getFullname();
        System.out.println(fullname == null);
    }

    @Scheduled(fixedDelay = 1000)
    public void fixedDelayTask(){
        System.out.println(System.currentTimeMillis());
    }
}
