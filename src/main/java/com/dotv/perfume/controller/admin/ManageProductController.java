package com.dotv.perfume.controller.admin;

import com.dotv.perfume.dto.ProductDTO;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String getProduct() {
        return "admin/product/product";
    }

    @GetMapping("/lst_product")
    public ResponseEntity<List<Product>> getListProduct(@RequestParam(required = false) String search){
        if(!search.equals("all")){
           return ResponseEntity.ok(productService.getListProduct(search));
        }
        List<Product> lstPro=productService.getListProduct("all").stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
                .collect(Collectors.toList());
       return ResponseEntity.ok(lstPro);
    }

    @PostMapping("/save_product")
    public ResponseEntity<JSONObject> saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO){
        JSONObject result = new JSONObject();
//        Path root = Paths.get("uploads");
//        result.put("file",root);
        return ResponseEntity.ok(result);
    }

}
