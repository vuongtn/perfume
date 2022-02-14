package com.dotv.perfume.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping("/product_cart")
    public String getProductInCart(){
        return "user/cart/cart";
    }
}
