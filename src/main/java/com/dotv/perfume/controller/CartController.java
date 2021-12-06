package com.dotv.perfume.controller;

import com.dotv.perfume.dto.CartDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.service.CartService;
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
    ModelMapper modelMapper;


    //Thêm sản phẩm vào giỏ hàng
    @PostMapping("/cart/add")
    public MessageResponse addProductToCart(@RequestBody CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        if(cartService.addProductToCart(cart))
            return new MessageResponse("Thêm sản phẩm vào giỏ hàng thành công");
        else
            return new MessageResponse("Thêm sản phẩm vào giỏ hàng thất bại");
    }


    //Hiển thị sản phẩm trong giỏ hàng theo id của tài khoản
    @GetMapping ("cart/show")
    public List<ProductInCartDTO> getProductInCart(@RequestParam("id") int id){
        return cartService.getProductInCart(id);
    }

    //Sửa số lượng sản phẩm trong giỏ
    @PutMapping("cart/edit")
    public MessageResponse editAmountProduct(@RequestBody CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        if(cartService.editAmountProduct(cart))
            return new MessageResponse("Cập nhật số lượng thành công");
        else
            return new MessageResponse("Cập nhật số lượng thất bại");
    }

    //Xóa sản phẩm trong giỏ hàng
    @DeleteMapping("cart/delete")
    public MessageResponse deleteProductInCart(@RequestBody CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        cartService.deleteProductInCart(cart);
            return new MessageResponse("Xóa sản phẩm trong giỏ thành công");
    }
}
