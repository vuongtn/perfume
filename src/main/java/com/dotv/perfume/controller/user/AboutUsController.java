package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class AboutUsController {
    @GetMapping("/introduce")
    public String getIntroduce(){
        return "user/about_us/introduce";
    }

    @GetMapping("/contact")
    public String getRegister(){
        return "user/about_us/contact";
    }
}
