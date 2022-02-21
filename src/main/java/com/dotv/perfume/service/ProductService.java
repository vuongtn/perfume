package com.dotv.perfume.service;

import com.dotv.perfume.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Page<Product> getListProductByTrademark(int idTrademark, Boolean status, int curPage, int page);
    List<Product> getListProduct(int idTrademark, Boolean status, int typeFilter, int curPage, int page);
}
