package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.utils.Pager;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageBrandController extends BaseController {
    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 5;
    private static final int BUTTONS_TO_SHOW = 5;

    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;

    @GetMapping("/brand")
    public String getBrand(){
//        List<Brand> lstBrand=brandService.getAllBrand();
//        List<Brand> lstBrand1=brandService.getAllBrand().stream()
//                        .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
//                .skip((curPage-1)*PAGE).limit(PAGE).collect(Collectors.toList());
//
//        model.addAttribute("lstBrand",lstBrand1);
//        int totalPage=(int)Math.ceil(lstBrand.size()/(float)PAGE);
//        Pager pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
//        model.addAttribute("totalPage", totalPage);
//        model.addAttribute("curPage", curPage);
//        model.addAttribute("pager",pager);

        return "admin/brand/brand";
    }
    @GetMapping("/lst_brand")
    public ResponseEntity<List<Brand>> getListBrand(){
        //List<Brand> lstBrand=brandService.getAllBrand();
        List<Brand> lstBrand=brandService.getAllBrand().stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lstBrand);
    }

    @PostMapping("/save_brand")
    public ResponseEntity<JSONObject> saveBrand(@ModelAttribute Brand brand){
        JSONObject result = new JSONObject();
        brandService.saveOrUpdateBrand(brand);
        result.put("message", Boolean.TRUE);
//        int sizeTrue = productService.getAllProductByTrademark(brand.getId(),true).size();
//        int sizeFalse = productService.getAllProductByTrademark(brand.getId(),false).size();
//        if(sizeFalse!=0||sizeTrue!=0){
//            result.put("message", Boolean.FALSE);
//        }
        return ResponseEntity.ok(result);
    }
}
