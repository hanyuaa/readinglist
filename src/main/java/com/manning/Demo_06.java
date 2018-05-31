package com.manning;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by hanyu on 2018/4/30.
 */
@Component
public class Demo_06 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        Optional<Integer> first = integers.stream().findFirst();

        System.out.println(first.toString());
    }
}
