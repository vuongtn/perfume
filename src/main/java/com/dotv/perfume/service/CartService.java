package com.dotv.perfume.service;

import com.dotv.perfume.entity.Cart;

public interface CartService {
    Boolean addProductToCart(Cart cart);
    //Cart getProductInCart(int idProduct);
}
