package com.dotv.perfume.service;

import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;

import java.util.List;

public interface CartService {
    Boolean addProductToCart(Cart cart);
    List<ProductInCartDTO> getProductInCart(int idAccount);
    Boolean editAmountProduct(Cart cart);
    void deleteProductInCart(Cart cart);
}
