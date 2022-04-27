package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")
public class HomeController extends BaseController {

    @Value("${user.page.home.amountNewPro}")
    int amountNewProduct;

    @Value("${user.page.home.amountSexPro}")
    int amountGenderProduct;

    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String getHome(Model model){
        List<Product> listNewProduct = productService.getListNewProduct(1,true).stream().limit(amountNewProduct).collect(Collectors.toList());
        List<Product> listBoyProduct =productService.getListNewProduct(2,true).stream().limit(amountGenderProduct).collect(Collectors.toList());
        List<Product> listGirlProduct = productService.getListNewProduct(3,true).stream().limit(amountGenderProduct).collect(Collectors.toList());
        List<Product> listUnisexProduct = productService.getListNewProduct(4,true).stream().limit(amountGenderProduct).collect(Collectors.toList());
        model.addAttribute("lstNewProduct",listNewProduct);
        model.addAttribute("lstBoyProduct",listBoyProduct);
        model.addAttribute("lstGirlProduct",listGirlProduct);
        model.addAttribute("lstUnisexProduct",listUnisexProduct);
        model.addAttribute("typeMenu",1);
        return "user/home/home";
    }


    //list thương hiệu với status = true;
    @GetMapping("/getMenu")
//    @ResponseBody
    public ResponseEntity<List<Brand>> getListTrademark(){
       return ResponseEntity.ok(brandService.getBrandByStatus(true));
    }



}
