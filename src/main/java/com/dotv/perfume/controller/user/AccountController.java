package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class AccountController {
    @GetMapping("/manage_acc")
    public String getAccManage(){
        return "user/account/manage_acc";
    }

    @GetMapping("/update_acc")
    public String getUpdateAcc(){
        return "user/account/update_acc";
    }

    @GetMapping("/update_pass")
    public String getUpdatePass(){
        return "user/account/update_pass";
    }

    @GetMapping("/order_acc")
    public String getOrderAcc(){
        return "user/account/order_acc";
    }

}
