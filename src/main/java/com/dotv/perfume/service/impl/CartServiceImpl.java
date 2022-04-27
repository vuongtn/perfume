package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<ProductInCartDTO> getProductInCart(int idUser) {
        return cartRepository.getProductInCart(idUser);
    }

    @Override
    public int addProToCart(Cart cart) {
        Optional<Cart> cartOld = cartRepository.findById(cart.getId());
        int type=0;//sản phẩm chưa có trong giỏ
        //kt cartOld có rỗng ko, isPresent() trả về false khi rỗng.
        if(cartOld.isPresent()){
            Product product = productService.getProductById(cart.getId().getIdProduct());
            if((cart.getAmount()+cartOld.get().getAmount())>product.getAmount()){
                return 10;
            }
            cart.setAmount(cart.getAmount()+cartOld.get().getAmount());
            type=1;//Sản phẩm đã có trong giỏ
        }
        if(!cartOld.isPresent()) {
            Product product = productService.getProductById(cart.getId().getIdProduct());
            if (cart.getAmount() > product.getAmount()) {
                return 10;
            }
        }
        cartRepository.save(cart);
        return type;
    }
}
