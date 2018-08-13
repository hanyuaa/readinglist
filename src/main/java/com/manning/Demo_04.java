package com.manning;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;

/**
 * Created by hanyu on 2018/4/17.
 */
public class Demo_04 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_04.class);

    public static void main(String[] args) throws IOException {
        String s = "timeline:user:123";
        int i = s.lastIndexOf(":");
        String substring = s.substring(0, i + 1);
        System.out.println(substring);
    }


    //将一行一行的写入set集合中,然后再写出去,格式是 你好=4
    public static HashSet<String> readFile() throws Exception {
        File file = new File("C:\\Users\\hanyu\\Desktop\\敏感词\\source2.txt");
        FileInputStream is = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        File out = new File("C:\\Users\\hanyu\\Desktop\\敏感词\\source3.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));

        String line;
        HashSet<String> set = Sets.newHashSet();
        int i = 1;
        while ((line = reader.readLine()) != null) {
            //line = line.substring(0,line.length()-2);
            //set.add(line);
            //System.out.println(i);
            //i++;
            line = line + "\r\n";
            writer.write(line);

        }
        /*for (String s : set) {
            s = s +"=4\r\n";
            writer.write(s);
        }*/
        //System.out.println(set.size()+"-------");

        reader.close();
        is.close();

        writer.close();

        return set;
    }
}
