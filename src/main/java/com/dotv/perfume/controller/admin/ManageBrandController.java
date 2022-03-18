package com.dotv.perfume.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageBrandController {
    @GetMapping("/brand")
    public String getListBrand(){
        return "admin/brand/brand";
    }
}
