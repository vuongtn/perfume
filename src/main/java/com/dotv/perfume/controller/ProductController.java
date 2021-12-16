package com.dotv.perfume.controller;

import com.dotv.perfume.entity.Image;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    /*
    * Trả về ds sản phẩm theo danh mục
    * */
    @GetMapping("/client/products")
    public Page<Product> getProcductByIdCategory(@RequestParam int idCategory, @RequestParam int currPage){
//        return  productService.getAllByCategory(id_ca,currPage).stream()
//                .map(product -> modelMapper.map(product,ProductDTO.class))
//                .collect(Collectors.toList());
        return productService.getAllByCategory(idCategory,currPage);
    }


    @GetMapping("/client/detail")//Trả về chi tiết sản phẩm theo mã sản phẩm
    public Product getProcductById(@RequestParam int id){
        return productService.getProductById(id);
    }


    @GetMapping("/client/search")//Tìm kếm theo tên
    public List<Product> getProductByName(@RequestParam String name){
        return productService.getAllProductByName(name);
    }

    //Xem thêm, sửa trạng thái sản phẩm.

    //Trả về tất cả sản phẩm có phân trang
    @GetMapping("/admin/products")
    public Page<Product> getProduct(@RequestParam int currPage){
        return productService.getAllProduct(currPage);
    }

    //Trả về sản phẩm theo id ( hàm trên)

    //Thêm mới sản phẩm
    @PostMapping("/admin/product/add")
    public MessageResponse addProduct(@RequestParam Map<String,Object> params, @RequestParam("files") MultipartFile[] files) throws IOException {
        Integer idCategory = Integer.valueOf((String)params.get("idCategory"));
        String name = (String) params.get("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong((String)params.get("price")));
        Integer gender = Integer.valueOf((String)params.get("idCategory"));
        String origin=(String) params.get("origin");
        String capacity=(String) params.get("capacity");
        Integer amount=Integer.valueOf((String)params.get("amount"));
        String shortDescription=(String) params.get("shortDescription");
        String detail=(String) params.get("detail");
        LocalDate createDate = LocalDate.now();
        Integer discount = Integer.valueOf((String)params.get("discount"));
        Boolean status = Boolean.valueOf((String) params.get("status"));


//        @Valid
        Product product = new Product(idCategory,name,price,gender,origin,capacity,amount,shortDescription,detail,createDate,discount,status);
        List<Image> images = new ArrayList<>();
        for(MultipartFile file:files){
            Image image = new Image();
            image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            if(file==files[0])
                image.setType("main");
            else
                image.setType("extra");
            images.add(image);
            image.setProduct(product);//set product cho từng thằng image
        }
        product.setImages(images);//set list image cho thằng product (1 sản phẩm có nhiều ảnh mà)


        MessageResponse message = new MessageResponse();
        if(productService.addProduct(product,files))
            message.setMessage("Thêm sản phẩm thành công");
        else
            message.setMessage("Thêm sản phẩm thất bại");
        return message;
    }

    @PutMapping("/admin/product/edit")
    public MessageResponse editProduct(@RequestParam Map<String,Object> params, @RequestParam("files") MultipartFile[] files) throws IOException {
        Integer idProduct = Integer.valueOf((String)params.get("idProduct"));
        Integer idCategory = Integer.valueOf((String)params.get("idCategory"));
        String name = (String) params.get("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong((String)params.get("price")));
        Integer gender = Integer.valueOf((String)params.get("idCategory"));
        String origin=(String) params.get("origin");
        String capacity=(String) params.get("capacity");
        Integer amount=Integer.valueOf((String)params.get("amount"));
        String shortDescription=(String) params.get("shortDescription");
        String detail=(String) params.get("detail");
        LocalDate createDate = LocalDate.now();
        Integer discount = Integer.valueOf((String)params.get("discount"));
        Boolean status = Boolean.valueOf((String) params.get("status"));


        Product product = new Product(idCategory,name,price,gender,origin,capacity,amount,shortDescription,detail,createDate,discount,status);
        product.setId(idProduct);
        List<Image> images = new ArrayList<>();
        for(MultipartFile file:files){
            Image image = new Image();
            image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            if(file==files[0])
                image.setType("main");
            else
                image.setType("extra");
            images.add(image);
            image.setProduct(product);//set product cho từng thằng image
        }
        product.setImages(images);//set list image cho thằng product (1 sản phẩm có nhiều ảnh mà)


        MessageResponse message = new MessageResponse();
        if(productService.addProduct(product,files))
            message.setMessage("Cập nhật sản phẩm thành công");
        else
            message.setMessage("Cập nhật sản phẩm thất bại");
        return message;
    }

}
