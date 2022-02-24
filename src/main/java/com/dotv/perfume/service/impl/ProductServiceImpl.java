package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.ProductRepository;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


//    @Override
//    public Page<Product> getListProductByTrademark(int idTrademark, Boolean status, int curPage, int page) {
//        return productRepository.findAllByIdTrademarkAndStatus(idTrademark,status, PageRequest.of(curPage-1, page, Sort.by("name").descending()));
//    }
//
    @Override
    public List<Product> getListProductByTrademark(int idTrademark, Boolean status, int typeFilter, int curPage, int page) {
        //Sắp xếp theo tăng dần theo giá
        if(typeFilter==1){
            return productRepository.getProductByTrademarkAscPrice(idTrademark,status, PageRequest.of(curPage-1, page));
        }
        //Sắp xếp giảm dần theo giá
        if(typeFilter==2){
            return productRepository.getProductByTrademarkDescPrice(idTrademark,status,PageRequest.of(curPage-1, page));
        }
        //Sắp theo tên
        if(typeFilter==3){
            return productRepository.getProductByTrademarkAscName(idTrademark,status,PageRequest.of(curPage-1, page));
        }
        return null;
    }

    @Override
    public List<Product> getListProductByGender(String gender, Boolean status, int typeFilter, int curPage, int page) {
        //Sắp xếp theo tăng dần theo giá
        if(typeFilter==1){
            return productRepository.getProductByGenderkAscPrice(gender,status, PageRequest.of(curPage-1, page));
        }
        //Sắp xếp giảm dần theo giá
        if(typeFilter==2){
            return productRepository.getProductByGenderkDescPrice(gender,status,PageRequest.of(curPage-1, page));
        }
        //Sắp theo tên
        if(typeFilter==3){
            return productRepository.getProductByGenderkAscName(gender,status,PageRequest.of(curPage-1, page));
        }
        return null;
    }

    @Override
    public List<Product> getListNewProduct(int typeName,Boolean status) {
        //get list sản phẩm sắp theo ngày
        if(typeName==1) {
            return productRepository.findAllByStatusOrderByCreatedDateDesc(status);
        }
        //get list sản phẩm nam
        if(typeName==2) {
            return productRepository.findAllByStatusAndGender(true,"Nam");
        }
        //get list sản phẩm nữ
        if(typeName==3) {
            return productRepository.findAllByStatusAndGender(true,"Nữ");
        }
        //get list sản phẩm unisex
        if(typeName==4){
            return productRepository.findAllByStatusAndGender(true,"Unisex");
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProductByTrademark(int id, Boolean status) {
        return productRepository.getProductByTrademark(id,status);
    }

    @Override
    public List<Product> getAllProductByStatus(Boolean status, int typeFilter, int curPage, int page) {
        //Sắp xếp theo tăng dần theo giá
        if(typeFilter==1){
            return productRepository.getProductAscPrice(status, PageRequest.of(curPage-1, page));
        }
        //Sắp xếp giảm dần theo giá
        if(typeFilter==2){
            return productRepository.getProductDescPrice(status, PageRequest.of(curPage-1, page));
        }
        //Sắp theo tên
        if(typeFilter==3){
            return productRepository.getProductAscName(status, PageRequest.of(curPage-1, page));
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct(Boolean status) {
        return productRepository.findAllByStatus(status);
    }


}
