package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.ProductRepository;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public Page<Product> getListProductByTrademark(int idTrademark, Boolean status, int curPage, int page) {
        return productRepository.findAllByIdTrademarkAndStatus(idTrademark,status, PageRequest.of(curPage-1, page, Sort.by("name").descending()));
    }

    @Override
    public List<Product> getListProduct(int idTrademark, Boolean status, int typeFilter, int curPage, int page) {
        //Sắp xếp theo tăng dần theo giá
        if(typeFilter==1){
            return productRepository.findAllByIdTrademarkAndStatusOrderByPriceAsc(idTrademark,status, PageRequest.of(curPage-1, page));
        }
        //Sắp xếp giảm dần theo giá
        if(typeFilter==2){
            return productRepository.findAllByIdTrademarkAndStatusOrderByPriceDesc(idTrademark,status,PageRequest.of(curPage-1, page));
        }
        //Sắp theo tên
        if(typeFilter==3){
            return productRepository.findAllByIdTrademarkAndStatusOrderByNameAsc(idTrademark,status,PageRequest.of(curPage-1, page));
        }
        return null;
    }


}
