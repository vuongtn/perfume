package com.dotv.perfume.controller.user;

import antlr.StringUtils;
import com.dotv.perfume.dto.PageCustom;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Trademark;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.TrademarkService;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;


@Controller
public class ProductController {

    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 4;

    @Autowired
    ProductService productService;
    @Autowired
    TrademarkService trademarkService;


    //Kích vào một tên thương hiệu => load các sp của thương hiệu đó
//    @GetMapping("/products")
//    public String getAllProduct(){
//        return "user/product/all_product";
//    }

    //Kích vào một tên thương hiệu => load các sp của thương hiệu đó
    @GetMapping("/product-by-trademark")
    public ResponseEntity<PageCustom> getProductGird(@RequestParam("idTrade") int idTrade, @RequestParam int typeFil, @RequestParam int curPage){
        PageCustom pageCustom = new PageCustom();
        List<Product> lstProduct = productService.getListProductByTrademark(idTrade,true,typeFil,curPage,PAGE);
        pageCustom.setCurPage(curPage);
        pageCustom.setTotalPage(productService.getAllProductByTrademark(idTrade,true).size());
        pageCustom.setTypeFil(typeFil);
        pageCustom.setData(lstProduct);
        return ResponseEntity.ok(pageCustom);
    }

    //Kích vào một tên thương hiệu => load các sp của thương hiệu đó
    //typeP: 1 lấy sản phẩm theo thương hiệu, typeP: 2 lấy sản phẩm theo giới tính
    @GetMapping("/products")
    public String getProductByTrade(@RequestParam int id,@RequestParam int sx, @RequestParam int typeP, @RequestParam int typeF, @RequestParam int curPage, Model model){
        List<Product> lstProduct=null;
        if(typeP==1) {
            lstProduct=productService.getListProductByTrademark(id, true, typeF, curPage, PAGE);
            int totalPage = productService.getAllProductByTrademark(id, true).size();
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("curPage", curPage);
            model.addAttribute("typeF", typeF);
            model.addAttribute("typeP",typeP);
            model.addAttribute("titleName", trademarkService.getTrademarkById(id));
            model.addAttribute("id",id);
        }
        if(typeP==2){
            //id:2,3,4 => Nam, Nữ, Unisex
            int totalPage = productService.getListNewProduct(sx,true).size();
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("curPage", curPage);
            model.addAttribute("typeF", typeF);
            model.addAttribute("typeP",typeP);
            model.addAttribute("sex",sx);
            String gender="";
            if(sx==2) {
                gender = "Nam";
                model.addAttribute("headName", "Nam");
            }
            if(sx==3) {
                gender = "Nữ";
                model.addAttribute("headName", "Nữ");
            }
            if(sx==4) {
                gender = "Unisex";
                model.addAttribute("headName", "Unisex");
            }
            lstProduct = productService.getListProductByGender(gender, true, typeF, curPage, PAGE);
        }
        model.addAttribute("lstPro", lstProduct);
        return "user/product/all_product";
    }


    @GetMapping("/single_product")
    public String getProductById(@RequestParam int id, Model model){
        Product product = productService.getProductById(id);
        Trademark trademark = product.getTrademark();
        model.addAttribute("singleProduct",product);
        model.addAttribute("nameTrademark",trademark);
        return "user/product/details_product";
    }
}
