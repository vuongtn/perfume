package com.dotv.perfume.controller.user;

import com.dotv.perfume.entity.Trademark;
import com.dotv.perfume.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")
public class HomeController {
    @Autowired
    TrademarkService trademarkService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("amountTrademark",1);
        return "user/home/home";
    }

    @GetMapping("/getMenu")
//    @ResponseBody
    public ResponseEntity<List<Trademark>> getListTrademark(){
       return ResponseEntity.ok(trademarkService.getTrademarkByStatus(true));
    }

    //


}
