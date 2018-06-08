package com.manning;

import com.wordsfilter.WordsFilterUtil;
import com.wordsfilter.result.FilteredResult;

/**
 * Created by hanyu on 2018/4/30.
 */
public class Demo_06 {
    public static void main(String[] args) throws Exception {
        String s = "同方";

        FilteredResult filteredResult = WordsFilterUtil.filterTextWithPunctuation(s, '*');
        System.out.println(filteredResult.getFilteredContent());
    }
}
