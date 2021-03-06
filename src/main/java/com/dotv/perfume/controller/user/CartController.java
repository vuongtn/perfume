package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.CartDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/per")
public class CartController extends BaseController {
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/product_cart")
    public String getProductInCart(Model model) throws Exception {
        int idUser = getUserLogined().getId();
        List<ProductInCartDTO> lstPro = cartService.getProductInCart(idUser);
        if(lstPro.size()==0){
            model.addAttribute("proInCart",1);
            return "user/cart/cart";
        }
        double totalPrice=0;
        for(ProductInCartDTO pro:lstPro){
                totalPrice = totalPrice + pro.getAmount() * pro.getPrice().doubleValue();
        }
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("lstPro",lstPro);
        return "user/cart/cart";
    }

    @PostMapping("/update_cart")
    public ResponseEntity<JSONObject> addOrUpdateCart(@RequestParam int type, @RequestParam int idPro, @RequestParam int amount) throws Exception {
        JSONObject result = new JSONObject();
        result.put("message", Boolean.FALSE);
        int idUser = getUserLogined().getId();
        Cart cart = new Cart(new CartId(idPro, idUser), amount);
        //C???p nh???t s??? l?????ng trong gi??? h??ng
        if(type==1) {
            Product product = productService.getProductById(cart.getId().getIdProduct());
            if(cart.getAmount()>product.getAmount()){
                result.put("message", 10);
                return ResponseEntity.ok(result);
            }
            cartRepository.save(cart);
            result.put("message", Boolean.TRUE);
        }
        //X??a s???n ph???m trong gi???
        if(type==2){
            cartRepository.delete(cart);
            result.put("totalPro", totalProInCart());
            result.put("message", Boolean.TRUE);
        }
        //Th??m s???n ph???m v??o gi???
        if(type==3){
            int mess = cartService.addProToCart(cart);
            result.put("message", mess);
        }
        return ResponseEntity.ok(result);
    }

}
