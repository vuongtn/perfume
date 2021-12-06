package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean addProductToCart(Cart cart) {
        Optional<Cart> cartOld =cartRepository.findById(cart.getId());
        //kt cartOld có rỗng ko, isPresent() trả về false khi rỗng.
        Boolean test = cartOld.isPresent();
        if(cartOld.isPresent()){
            cart.setAmount(cart.getAmount()+cartOld.get().getAmount());
        }
        if(cartRepository.save(cart)!=null)
          return true;
        return false;
    }

    @Override
    public List<ProductInCartDTO> getProductInCart(int idAccount) {
        return cartRepository.getProductInCart(idAccount);
    }

    @Override
    public Boolean editAmountProduct(Cart cart) {
        if(cartRepository.save(cart)!=null)
            return true;
        return false;
    }

    @Override
    public void deleteProductInCart(Cart cart) {
        cartRepository.delete(cart);
    }


}
