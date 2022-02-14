package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "user/home/home";
    }
//    public String getAllProduct(){
//        return "user/fragments/layout";
//    }

}
