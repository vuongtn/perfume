package com.dotv.perfume.controller;

import com.dotv.perfume.dto.CartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.service.impl.CartServiceImpl;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/cart/getProduct")
    public List<Cart> getCart(){
        return cartRepository.findAll();
    }
    @PostMapping("/cart/add")
    public Cart addProductToCart(@RequestBody Cart cart){
//        CartId cartId = new CartId(cartDTO.getIdProduct(),cartDTO.getIdAccount());
//        Cart cart = new Cart(cartId,cartDTO.getAmount());
//        if(cartService.addProductToCart(cart))
//            return new MessageResponse("Thêm sản phẩm vào giỏ hàng thành công");
//        else
//            return new MessageResponse("Thêm sản phẩm vào giỏ hàng thất bại");
        return cartRepository.save(cart);
    }
}
