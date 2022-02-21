package com.dotv.perfume.controller.user;

import com.dotv.perfume.dto.PageCustom;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ProductController {

    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 1;

    @Autowired
    ProductService productService;


    //Kích vào một tên thương hiệu => load các sp của thương hiệu đó
    @GetMapping("/all_product")
    public String getAllProduct(){

        //Page<Product> lstProduct = productService.getListProductByTrademark(idTrade,true,curPage,PAGE);
        //model.addAttribute("lstProductByTrademark",lstProduct);
        return "user/product/all_product";
    }

    //Kích vào một tên thương hiệu => load các sp của thương hiệu đó
    @GetMapping("/product-by-trademark")
    public ResponseEntity<PageCustom> test(@RequestParam("idTrade") int idTrade, @RequestParam int typeFil, @RequestParam int curPage){
        PageCustom pageCustom = new PageCustom();
        List<Product> lstProduct = productService.getListProduct(idTrade,true,typeFil,curPage,PAGE);
        pageCustom.setCurPage(curPage);
        pageCustom.setTotalPage(lstProduct.size());
        pageCustom.setData(lstProduct);
        return ResponseEntity.ok(pageCustom);
    }

    @GetMapping("/single_product")
    public String getProductById(){
        return "user/product/details_product";
    }
}
