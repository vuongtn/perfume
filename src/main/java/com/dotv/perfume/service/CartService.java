package com.dotv.perfume.service;

import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.untils.Message;

public interface CartService {
    String addProductToCart(Cart cart);
    //Cart getProductInCart(int idProduct);
}
