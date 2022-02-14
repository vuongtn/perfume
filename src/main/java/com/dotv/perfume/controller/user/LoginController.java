package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class LoginController {
    @GetMapping("/login")
    public String getLogin(){
        return "user/login/login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return "user/register/register";
    }

}
