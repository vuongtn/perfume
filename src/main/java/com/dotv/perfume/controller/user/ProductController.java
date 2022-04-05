package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.FilterProductDTO;
import com.dotv.perfume.utils.PageCustom;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ProductController extends BaseController {


    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;


    @GetMapping("/single_product")
    public String getProductById(@RequestParam int id, Model model){
        Product product = productService.getProductById(id);
        Brand trademark = product.getBrand();
        model.addAttribute("singleProduct",product);
        model.addAttribute("nameTrademark",trademark);
        return "user/product/details_product";
    }


    //list thương hiệu với status = true;
    @GetMapping("/all_brand")
    public ResponseEntity<List<Brand>> getListBrand(){
        return ResponseEntity.ok(brandService.getBrandByStatus(true));
    }


    /*
    * Trả về trang tất cả sản phẩm
    * type=1: Tất cả sản phẩm
    * type=2: Sản phẩm theo brand
    * type=3: Sản phẩm theo giới tính
    * type=4: Sản phẩm theo từ khóa search
    * */
    @GetMapping("/products")
    public String getPro(@RequestParam int type, @RequestParam(required = false) String query, @RequestParam(required = false) String id, @RequestParam(required = false) String se, @RequestParam(required = false) String menu, Model model){
        if(type==4 && StringUtils.isBlank(query)){
            return "redirect:/";
        }
        model.addAttribute("type",type);
        model.addAttribute("idBrand","");
        if(StringUtils.isNotBlank(menu)){
            model.addAttribute("typeMenu",Integer.valueOf(menu));
        }
        if(StringUtils.isNotBlank(id)) {
            model.addAttribute("idBrand", Integer.valueOf(id));
            model.addAttribute("nameBrand",brandService.getBrandById(Integer.valueOf(id)).getName());
        }
        model.addAttribute("sex",se);
        model.addAttribute("search",query);
        return "user/product/all_product";
    }


    //api get list product and filter
    @PostMapping("/list_pro_filter")
    public ResponseEntity<List<Product>> getAllProFilter(@ModelAttribute FilterProductDTO filterProductDTO){
        return ResponseEntity.ok(productService.getListProductByFilter(filterProductDTO));
    }


}
