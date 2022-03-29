package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageUserController extends BaseController {

    @GetMapping("/user")
    public String getUser(){
        return "admin/user/user";
    }
}
