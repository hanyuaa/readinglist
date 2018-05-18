package com.manning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by hanyu on 2018/4/25.
 */
public class Demo_5 {

    public Demo_5() throws IOException {
    }

    public static String processFile(BufferedReaderProcessor p)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("D:\\Fiddler\\credits.txt"))){
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String s = processFile((BufferedReader br) -> br.readLine()+br.readLine()+br.readLine());
    }


}

interface BufferedReaderProcessor {

    String process(BufferedReader bf)throws IOException;

}
