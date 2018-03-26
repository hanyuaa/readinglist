package com.manning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hanyu on 2018/3/24.
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    private ReadingListRespository readingListRespository;

    @Autowired
    public ReadingListController(ReadingListRespository readingListRespository) {
        this.readingListRespository = readingListRespository;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model){

        List<Book> readingList = readingListRespository.findByReader(reader);

        if (readingList != null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRespository.save(book);
        return "redirect:/readingList/{reader}";
    }
}
