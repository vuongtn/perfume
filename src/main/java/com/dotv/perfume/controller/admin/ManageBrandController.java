package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.utils.Pager;
import com.dotv.perfume.utils.PerfumeUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public ResponseEntity<JSONObject> saveBrand(@ModelAttribute Brand brand) throws Exception {
        JSONObject result = new JSONObject();
        PerfumeUtils perfumeUtils = new PerfumeUtils();
        if(brand.getId()==null){
            brand.setCreatedDate(perfumeUtils.getDateNow());
//            brand.setCreatedBy(getUserLogined().getFullName());
        }
        else{
//            brand.setUpdatedBy(getUserLogined().getFullName());
            brand.setUpdatedDate(perfumeUtils.getDateNow());
        }
        brandService.saveOrUpdateBrand(brand);
        result.put("message", Boolean.TRUE);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete_brand")
    public ResponseEntity<JSONObject> deleteBrand(@RequestParam int id){
        JSONObject result = new JSONObject();
        result.put("message", Boolean.TRUE);
        int sizeTrue = productService.getAllProductByTrademark(id,true).size();
        int sizeFalse = productService.getAllProductByTrademark(id,false).size();
        if(sizeFalse!=0||sizeTrue!=0){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        brandService.deleteBrand(id);
        return ResponseEntity.ok(result);
    }

}
