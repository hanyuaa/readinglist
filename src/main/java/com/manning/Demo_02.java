package com.manning;

import java.util.Calendar;

/**
 * Created by hanyu on 2018/4/9.
 */
public class Demo_02 {
    public static void main(String[] args) throws Exception {
        Calendar checkPoint = Calendar.getInstance();
        checkPoint.set(Calendar.DATE, checkPoint.get(Calendar.DATE) - 180);
        checkPoint.set(Calendar.HOUR_OF_DAY, 0);
        checkPoint.set(Calendar.MINUTE, 0);
        checkPoint.set(Calendar.SECOND, 0);

        System.out.println(checkPoint.getTime()
        );

    }
}
