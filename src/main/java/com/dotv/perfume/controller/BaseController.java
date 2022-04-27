package com.dotv.perfume.controller;

import com.dotv.perfume.dto.ProductInBillDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.CartRepository;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@Controller
public abstract class BaseController {
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @ModelAttribute("totalProInCart")
    @Transactional
    public int totalProInCart() throws Exception {
        if(isLogined()==true){
            int idUser = getUserLogined().getId();
            //Kiểm tra product đó số lượng <= 0 thì ẩn
            List<ProductInCartDTO> lstPro = cartService.getProductInCart(idUser);
            for(ProductInCartDTO pro:lstPro){
                Product product = productService.getProductById(pro.getId());
                if(product.getAmount()<=0){
                    //lstPro.remove(pro);
                    cartRepository.deleteById(new CartId(pro.getId(),idUser));
                }
                else {
                    if (product.getAmount() < pro.getAmount()) {
                        cartRepository.updateAmountCart(new CartId(pro.getId(), idUser), product.getAmount());
                    }
                }
            }

            return cartService.getProductInCart(idUser).size();
        }
        return 0;
    }

    @ModelAttribute("isLogined")
    public boolean isLogined() throws Exception {
        boolean isLogined = false;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if(getUserLogined().getType().equals("GUEST")) {
                isLogined = true;
            }
        }
        return isLogined;
    }

    @ModelAttribute("userLogined")
    public User getUserLogined() throws Exception {
        Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userLogined != null && userLogined instanceof UserDetails) {
            User user = (User) userLogined;
            User userBD = userService.getUserById(user.getId());
            user.setStatus(userBD.getStatus());
            user.setCreatedBy(userBD.getCreatedBy());
            user.setUpdatedBy(userBD.getUpdatedBy());
            user.setCreatedDate(userBD.getCreatedDate());
            user.setUpdatedDate(userBD.getUpdatedDate());

            user.setUsername(userBD.getUsername());
            user.setPassword(userBD.getPassword());
            user.setEmail(userBD.getEmail());
            user.setFullName(userBD.getFullName());
            user.setAddress(userBD.getAddress());
            user.setPhone(userBD.getPhone());
            user.setPermission(userBD.getPermission());
            user.setType(userBD.getType());
            return user;
        }
        return null;
    }

//    @ModelAttribute("categoryRole")
//    public UserRole getCategoryRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MC")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("productRole")
//    public UserRole getProductRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MP")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("categoryBlogRole")
//    public UserRole getCategoryBlofRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MCB")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("blogRole")
//    public UserRole getBlogRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MB")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("orderRole")
//    public UserRole getOrderRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MO")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("accountRole")
//    public UserRole getAccountRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MA")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("introduceRole")
//    public UserRole getIntroduceRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MI")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("contactRole")
//    public UserRole getContactRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("MC")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
//
//    @ModelAttribute("statiscalRole")
//    public UserRole getStatiscalRole() throws Exception {
//        User user = getUserLogined();
//        if (user != null) {
//            for (UserRole userRole : user.getUserRoles()) {
//                if (userRole.getRole().getCode().equalsIgnoreCase("VS")) {
//                    return userRole;
//                }
//            }
//        }
//        return null;
//    }
}
