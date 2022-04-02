package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.dto.NewsDTO;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.entity.News;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.service.NewsService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManageNewController extends BaseAdminController {
    @Autowired
    NewsService newsService;

    @Autowired
    PerfumeUtils perfumeUtils;

    @GetMapping("/introduce")
    @PreAuthorize("hasAuthority('ADMIN_MI')")
    public String getIntroduce(Model model) {
        if(newsService.getListNew(0).size()>0) {
            model.addAttribute("idIntroduce", newsService.getListNew(0).get(0).getId());
            model.addAttribute("introduce", newsService.getListNew(0).get(0).getDetailDescription());
        }
        else{
            model.addAttribute("introduce","");
        }
        return "admin/introduce/introduce";
    }

    @PostMapping("/save_introduce")
    @PreAuthorize("hasAuthority('ADMIN_MI')")
    public ResponseEntity<JSONObject> saveIntroduce(News news){
        JSONObject result = new JSONObject();
        news.setStatus(0);
        if(news.getId()==null){
            news.setCreatedDate(perfumeUtils.getDateNow());
//           news.setCreatedBy(getUserLogined().getFullName());
        }
        else{
//           news.setUpdatedBy(getUserLogined().getFullName());
            news.setUpdatedDate(perfumeUtils.getDateNow());
        }
        try{
            newsService.saveIntroduce(news);
            result.put("message",Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message",Boolean.TRUE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/news")
    @PreAuthorize("hasAuthority('ADMIN_MN')")
    public String getNews(){
        return "admin/news/news";
    }

    @GetMapping("/lst_news")
    @PreAuthorize("hasAuthority('ADMIN_MN')")
    public ResponseEntity<List<News>> getListNews(){
        //List<Brand> lstBrand=brandService.getAllBrand();
        List<News> lstNews=newsService.getListNew(-1);
        return ResponseEntity.ok(lstNews);
    }

    @PostMapping("/save_news")
    @PreAuthorize("hasAuthority('ADMIN_MN')")
    public ResponseEntity<JSONObject> saveNews(@ModelAttribute NewsDTO newsDTO) throws Exception {
        JSONObject result = new JSONObject();
        PerfumeUtils perfumeUtils = new PerfumeUtils();
        try {
            User user = getUserLogined();
            if (newsDTO.getId() == null) {
                newsDTO.setCreatedDate(perfumeUtils.getDateNow());
                newsDTO.setCreatedBy(user.getFullName());
                result.put("message", 1);
            } else {
                newsDTO.setUpdatedDate(perfumeUtils.getDateNow());
                newsDTO.setUpdatedBy(user.getFullName());
                result.put("message", 2);
            }
            newsService.saveNews(newsDTO);
        }
        catch (Exception e){
            result.put("message", 3);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get_news_id")
    public ResponseEntity<News> getNewsById(@RequestParam int id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping("/delete_news")
    @PreAuthorize("hasAuthority('ADMIN_MN')")
    public ResponseEntity<JSONObject> deleteNews(@RequestParam int id){
        JSONObject result = new JSONObject();
        result.put("message", Boolean.TRUE);
        try {
            newsService.deleteNewsById(id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }
}
