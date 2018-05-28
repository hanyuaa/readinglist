package com.manning;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hanyu on 2018/4/17.
 */
public class Demo_04 {

    public static void main(String[] args) {
        HashSet<Integer> set = Sets.newHashSet();
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream().filter(e -> !set.contains(e)).collect(Collectors.toList());

        System.out.println(collect);
    }

}
