package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/all_product")
    public String getAllProduct(){
        return "user/product/all_product";
    }

    @GetMapping("/single_product")
    public String getProductById(){
        return "user/product/details_product";
    }
}
