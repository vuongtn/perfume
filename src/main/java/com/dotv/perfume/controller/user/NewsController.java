package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class NewsController {
    @GetMapping("/news")
    public String getAllProduct(){
        return "user/news/news";
    }

    @GetMapping("/single_news")
    public String getProductById(){
        return "user/news/news_detail";
    }
}
