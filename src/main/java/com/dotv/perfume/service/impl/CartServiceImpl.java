package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Boolean addProductToCart(Cart cart) {
        if(cartRepository.save(cart)!=null)
          return true;
        return false;
    }
}
