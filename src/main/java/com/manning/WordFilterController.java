package com.manning;

import com.wordsfilter.WordsFilterUtil;
import com.wordsfilter.result.FilteredResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanyu on 2018/6/8.
 */
@Controller
public class WordFilterController {

    @GetMapping("/wordFilter/{content}")
    @ResponseBody
    public Map wordFilter(@PathVariable String content){
        long start = System.currentTimeMillis();
        FilteredResult filteredResult = WordsFilterUtil.filterHtml(content, '*');
        int length = filteredResult.getBadWords().split(",").length;
        if (length >= 3) return null;

        String filteredContent = filteredResult.getFilteredContent();
        Map<String,String> map = new HashMap<>();
        map.put("data",filteredContent);
        map.put("success","ok");
        long end = System.currentTimeMillis();
        map.put("time",String.valueOf(end - start));
        return map;
    }
}
