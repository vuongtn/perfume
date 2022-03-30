package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.News;
import com.dotv.perfume.service.NewsService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageNewController extends BaseAdminController {
    @Autowired
    NewsService newsService;

    @Autowired
    PerfumeUtils perfumeUtils;

    @GetMapping("/introduce")
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
            newsService.saveNews(news);
            result.put("message",Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message",Boolean.TRUE);
        }
        return ResponseEntity.ok(result);
    }
}
