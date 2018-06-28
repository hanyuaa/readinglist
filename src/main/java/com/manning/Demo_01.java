package com.manning;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Demo_01 {
    public static void main(String[] args) {
        String[] str1 = {"fz", "su", "sy", "job", "zz", "bj", "hf", "wh", "qd", "others", "nj", "cs", "ty", "hrb", "xm", "xa", "cq", "nn", "cc", "tj"
                , "sjz", "wx", "jn", "jincheng", "sz", "dg", "dl", "sh", "gz", "cd", "hz", "hu", "baoding", "tangshan", "langfang", "handan"
                , "qinhuangdao", "cangzhou", "xingtai", "hengshui", "zhangjiakou", "chengde", "linfen", "datong", "yuncheng", "jingzhong"
                , "changzhi"};
        String[] str2 = {
                "baoding", "tangshan", "langfang", "handan", "qinhuangdao", "cangzhou", "xingtai", "hengshui", "zhangjiakou"
                , "chengde", "linfen", "datong", "yuncheng", "jingzhong", "changzhi"
        };
        String[] str3 = {
                "zjk"
        };
        String[] str4 = {
                "bd", "cz", "xz", "nt", "nb", "wz", "yz", "jh", "fs", "zs", "zh", "huizhou", "qz", "nc", "gy", "mianyang", "km", "luoyang"
                , "xx", "ny", "linyi", "dz", "wf", "lz"
        };
        String[] str5 = {
                "tangshan", "qinhuangdao", "handan", "xingtai", "zjk", "chengde", "cangzhou", "langfang", "hengshui", "datong",
                "changzhi", "jingzhong", "yuncheng", "linfen", "datong", "changzhi", "jingzhong", "yuncheng", "linfen"
        };

        //HashSet<String> set = Sets.newHashSet(str1);
        ArrayList<String> list = Lists.newArrayList(str1);
        list.addAll(Arrays.asList(str2));
        list.addAll(Arrays.asList(str3));
        list.addAll(Arrays.asList(str4));
        list.addAll(Arrays.asList(str5));
        System.out.println(list.size());
    }
}