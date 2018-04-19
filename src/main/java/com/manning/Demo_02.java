package com.manning;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hanyu on 2018/4/9.
 */
public class Demo_02 {
    public static void main(String[] args){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

        List<Integer> collect = costBeforeTax.stream().filter(x -> x > 200).collect(Collectors.toList());
        System.out.print(collect);

    }

    public static void filter(List<String> names, Predicate<String> condition) {

        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
