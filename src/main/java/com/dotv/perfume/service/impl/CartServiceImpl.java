package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.CartDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MessageResponse addProductToCart(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        Optional<Cart> cartOld =cartRepository.findById(cart.getId());
        //kt cartOld có rỗng ko, isPresent() trả về false khi rỗng.
        Boolean test = cartOld.isPresent();
        if(cartOld.isPresent()){
            cart.setAmount(cart.getAmount()+cartOld.get().getAmount());
        }
        if(cartRepository.save(cart)!=null)
            return new MessageResponse("Thêm sản phẩm vào giỏ hàng thành công");
        return new MessageResponse("Thêm sản phẩm vào giỏ hàng thất bại");
    }

    @Override
    public List<ProductInCartDTO> getProductInCart(int idAccount) {
        return cartRepository.getProductInCart(idAccount);
    }

    @Override
    public MessageResponse editAmountProduct(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        if(cartRepository.save(cart)!=null)
            return new MessageResponse("Cập nhật số lượng thành công");
        return new MessageResponse("Cập nhật số lượng thất bại");
    }

    @Override
    public MessageResponse deleteProductInCart(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        cartRepository.delete(cart);
        return new MessageResponse("Xóa sản phẩm trong giỏ thành công");
    }


}
