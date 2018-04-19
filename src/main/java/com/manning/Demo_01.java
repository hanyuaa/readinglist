package com.manning;

import java.util.Arrays;

/**
 * Created by hanyu on 2018/4/3.
 */
public class Demo_01 {
    public static void main(String[] args) {
        Day day = Day.MONDAY;
        Day day1 = Enum.valueOf(Day.class, day.name());
        Day[] days = Day.values();
        Enum e = day;
        Class aClass = e.getDeclaringClass();
        boolean anEnum = aClass.isEnum();
        Day[] enumConstants = (Day[]) aClass.getEnumConstants();

        System.out.print(anEnum+" "+Arrays.toString(enumConstants));
    }
}

//定义枚举类型
enum Day {
    MONDAY,TUESDAY,WEDNESDAY,
    THURSDAY,FRIDAY,SATURDAY,SUNDAY
}