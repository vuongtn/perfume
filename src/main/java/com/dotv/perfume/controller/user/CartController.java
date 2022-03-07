package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController extends BaseController {
    @GetMapping("/product_cart")
    public String getProductInCart(){
        return "user/cart/cart";
    }
}
