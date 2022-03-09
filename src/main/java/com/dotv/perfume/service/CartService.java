package com.dotv.perfume.service;

import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;

import java.util.List;

public interface CartService {
    List<ProductInCartDTO> getProductInCart(int idUser);
    int addProToCart(Cart cart);

}
