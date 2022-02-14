package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class BillController {
    @GetMapping("/buy")
    public String getFormBuy(){
        return "user/bill/bill";
    }
//    public String getAllProduct(){
//        return "user/fragments/layout";
//    }

}
