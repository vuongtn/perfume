package com.dotv.perfume.service;
import com.dotv.perfume.dto.FilterProductDTO;
import com.dotv.perfume.dto.ProductDTO;
import com.dotv.perfume.entity.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ProductService {
    //Page<Product> getListProductByTrademark(int idTrademark, Boolean status, int curPage, int page);
    List<Product> getListProductByTrademark(int idTrademark, Boolean status, int typeFilter, int curPage, int page);
    List<Product> getListProductByGender(String gender, Boolean status, int typeFilter, int curPage, int page);
    List<Product> getListNewProduct(int typeName,Boolean status);
    Product getProductById(int id);
    List<Product> getAllProductByTrademark(int id, Boolean status);
    List<Product> getAllProductByStatus(Boolean status, int typeFilter, int curPage, int page);
    List<Product> getAllProduct(Boolean status);
    List<Product> searchProductByName(String query,Boolean status);

    //filter product
    List<Product> getListProductByFilter(FilterProductDTO f);

    //admin
    //get all product or search
    List<Product> getListProduct(String search);
    Product saveOrUpdate(ProductDTO productDTO) throws IOException;
    void deleteProduct(int id);


}
