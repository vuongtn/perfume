package com.dotv.perfume.service;

import com.dotv.perfume.dto.CartDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.untils.MessageResponse;

import java.util.List;

public interface CartService {
    MessageResponse addProductToCart(CartDTO cartDTO);
    List<ProductInCartDTO> getProductInCart(int idAccount);
    MessageResponse editAmountProduct(CartDTO cartDTO);
    MessageResponse deleteProductInCart(CartDTO cartDTO);
}
