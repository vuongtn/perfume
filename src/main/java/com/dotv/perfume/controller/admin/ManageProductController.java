package com.dotv.perfume.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageProductController {

    @GetMapping("/product")
    public String getProduct() {
        return "admin/product/product";
    }
}
