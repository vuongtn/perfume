package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.utils.Pager;
import com.dotv.perfume.utils.PerfumeUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN_MB')")
public class ManageBrandController extends BaseAdminController {
    //Số phần tử hiển thị 1 trang
//    private static final int PAGE = 5;
//    private static final int BUTTONS_TO_SHOW = 5;

    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;

    @Value("${admin.page.brand}")
    String pageSize;

    @GetMapping("/brand")
    public String getBrand(Model model){
        model.addAttribute("typeMenu",2);
        model.addAttribute("pageSize",pageSize);
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
    public ResponseEntity<JSONObject> saveBrand(@ModelAttribute Brand brand) {
        JSONObject result = new JSONObject();
        brandService.saveOrUpdateBrand(brand);
        result.put("message", Boolean.TRUE);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete_brand")
    public ResponseEntity<JSONObject> deleteBrand(@RequestParam int id){
        JSONObject result = new JSONObject();
        result.put("message", Boolean.TRUE);
        int sizeTrue = productService.getAllProductByBrandAndStatus(id,true).size();
        int sizeFalse = productService.getAllProductByBrandAndStatus(id,false).size();
        if(sizeFalse!=0||sizeTrue!=0){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        brandService.deleteBrand(id);
        return ResponseEntity.ok(result);
    }

}
