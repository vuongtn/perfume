package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.utils.PageCustom;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ProductController extends BaseController {

    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 8;
    private static final int BUTTONS_TO_SHOW = 5;
    private static final String START_NAME = "Nước hoa ";

    @Autowired
    ProductService productService;
    @Autowired
    BrandService trademarkService;


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
    public String getProductByTradeOrGenderOrAll(@RequestParam int id,@RequestParam int sx, @RequestParam int typeP, @RequestParam int typeF, @RequestParam int curPage,@RequestParam(required = false) String typeM, Model model){
        List<Product> lstProduct=null;
        int totalPage=0;
        int totalElement;
        Pager pager=null;
        if(typeP==1) {
            lstProduct=productService.getListProductByTrademark(id, true, typeF, curPage, PAGE);
            totalElement=productService.getAllProductByTrademark(id, true).size();
            totalPage=(int)Math.ceil(totalElement/(float)PAGE);
            pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
            model.addAttribute("headName", START_NAME+trademarkService.getTrademarkById(id).getName());
        }
        if(typeP==2){
            //id:2,3,4 => Nam, Nữ, Unisex
            totalElement = productService.getListNewProduct(sx,true).size();
            totalPage=(int)Math.ceil(totalElement/(float)PAGE);
            pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
            String gender="";
            if(sx==2) {
                gender = "Nam";
                model.addAttribute("headName",START_NAME+"Nam");
            }
            if(sx==3) {
                gender = "Nữ";
                model.addAttribute("headName",START_NAME+ "Nữ");
            }
            if(sx==4) {
                gender = "Unisex";
                model.addAttribute("headName", START_NAME+"Unisex");
            }
            lstProduct = productService.getListProductByGender(gender, true, typeF, curPage, PAGE);
        }
        if(typeP==3) {
            lstProduct=productService.getAllProductByStatus(true, typeF,curPage,PAGE);
            totalElement = productService.getAllProduct(true).size();
            totalPage=(int)Math.ceil(totalElement/(float)PAGE);
            pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
            model.addAttribute("headName", "Tất cả sản phẩm");
            model.addAttribute("typeMenu",Integer.parseInt(typeM));
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("typeF", typeF);
        model.addAttribute("typeP",typeP);
        model.addAttribute("id",id);
        model.addAttribute("sex",sx);
        model.addAttribute("pager",pager);
        model.addAttribute("lstPro", lstProduct);
        return "user/product/all_product";
    }

    @GetMapping("/single_product")
    public String getProductById(@RequestParam int id, Model model){
        Product product = productService.getProductById(id);
        Brand trademark = product.getBrand();
        model.addAttribute("singleProduct",product);
        model.addAttribute("nameTrademark",trademark);
        return "user/product/details_product";
    }

    //Search product
    @GetMapping("search")
    public String getProductByQuery(@RequestParam String query,@RequestParam(required = false) String curPage, Model model){
        int currPage=1;
        if(curPage!=null){
            currPage=Integer.parseInt(curPage);
        }
        List<Product> totalPro=productService.searchProductByName(query,true);
        List<Product> lstPro=totalPro.stream().skip((currPage-1)*PAGE).limit(PAGE).collect(Collectors.toList());
        int totalPage=(int)Math.ceil(totalPro.size()/(float)PAGE);
        Pager pager = new Pager(totalPage,currPage-1, BUTTONS_TO_SHOW);
        model.addAttribute("totalPro",totalPro.size());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", currPage);
        model.addAttribute("lstPro",lstPro);
        model.addAttribute("pager",pager);
        model.addAttribute("query",query);
        return "user/product/search_product";
    }
}
