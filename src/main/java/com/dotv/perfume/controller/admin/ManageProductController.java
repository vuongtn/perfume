package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.dto.FilterProductAdminDTO;
import com.dotv.perfume.dto.ProductDTO;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN_MP')")
public class ManageProductController extends BaseAdminController {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @Value("${admin.page.product}")
    String pageSize;

    @GetMapping("/product")
    public String getProduct(Model model) {
        model.addAttribute("pageSize",pageSize);
        return "admin/product/product";
    }

//    @GetMapping("/lst_product")
//    public ResponseEntity<List<Product>> getListProduct(@RequestParam(required = false) String search){
//        if(!search.equals("all")){
//           return ResponseEntity.ok(productService.getListProduct(search.trim()));
//        }
//        List<Product> lstPro=productService.getListProduct("all").stream()
//                .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
//                .collect(Collectors.toList());
//       return ResponseEntity.ok(lstPro);
//    }

    @GetMapping("/lst_product")
    public ResponseEntity<List<ProductDTO>> getListProduct(@ModelAttribute FilterProductAdminDTO f){
        return ResponseEntity.ok(productService.getListProductByFilterAdmin(f));
    }

    @GetMapping("/lst_brand_pro")
    public ResponseEntity<List<Brand>> getListBrand(){
        //List<Brand> lstBrand=brandService.getAllBrand();
        List<Brand> lstBrand=brandService.getAllBrand().stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lstBrand);
    }
    @PostMapping("/save_product")
    public ResponseEntity<JSONObject> saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        JSONObject result = new JSONObject();
        try{
            if(productDTO.getId()==null) {
//                productDTO.setCreatedBy(getUserLogined().getUsername());
                result.put("message", 1);
            }
            else{//update
//                productDTO.setCreatedBy(getUserLogined().getUsername());
                result.put("message", 2);
            }
            productService.saveOrUpdate(productDTO);
        }
        catch (Exception e){
            result.put("message", 3);
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/get_product_id")
    public ResponseEntity<Product> getProductById(@RequestParam int id) {
        productService.getProductById(id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/delete_product")
    public ResponseEntity<JSONObject> deleteProduct(@RequestParam int id){
        JSONObject result = new JSONObject();
        result.put("message", Boolean.TRUE);
        try {
            productService.deleteProduct(id);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);   
    }

}
