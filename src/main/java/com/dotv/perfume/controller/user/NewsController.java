package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.News;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.service.NewsService;
import com.dotv.perfume.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")
public class NewsController extends BaseController {
    //Số phần tử hiển thị 1 trang
    private static final int BUTTONS_TO_SHOW = 5;

    @Autowired
    NewsService newsService;

    @Value("${user.page.news}")
    int page;

    @GetMapping("/news")
    public String getAllNews(@RequestParam int curPage, Model model){
        List<News> allNews=newsService.getListNew(1);
        List<News> lstNews=allNews.stream().skip((curPage-1)*page).limit(page).collect(Collectors.toList());
        int totalPage=(int)Math.ceil(allNews.size()/(float)page);

        Pager pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
        model.addAttribute("totalNews",allNews.size());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("lstNews",lstNews);
        model.addAttribute("pager",pager);
        model.addAttribute("typeMenu",4);
        return "user/new/news";
    }

    @GetMapping("/single_news")
    public String getNewsById(@RequestParam int id, Model model){
        model.addAttribute("news",newsService.getNewsById(id));
        model.addAttribute("typeMenu",4);
        return "user/new/news_detail";
    }
}
