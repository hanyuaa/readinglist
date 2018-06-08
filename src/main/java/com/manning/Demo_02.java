package com.manning;

import com.wordsfilter.WordsFilterUtil;
import com.wordsfilter.result.FilteredResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hanyu on 2018/4/9.
 */
public class Demo_02 {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        String s = "";
        FilteredResult filteredResult = WordsFilterUtil.filterHtml(s, '*');

        String badWords = filteredResult.getBadWords();
        String filteredContent = filteredResult.getFilteredContent();
        String goodWords = filteredResult.getGoodWords();

        String[] split = badWords.split(",");
        System.out.println(badWords+split.length);
        System.out.println(filteredContent);
        System.out.println(goodWords);
        System.out.println((System.currentTimeMillis() - start));


        /*InputStream inputStream = Demo_02.class.getResourceAsStream("/sensitive-words.dict");


        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader1 = new BufferedReader(reader);
        String line = "";
        while ((line = reader1.readLine())!=null){
            System.out.println(line);
        }

        reader1.close();
        reader.close();
        inputStream.close();*/
    }
}
