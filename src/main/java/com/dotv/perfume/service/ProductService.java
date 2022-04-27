package com.dotv.perfume.service;
import com.dotv.perfume.dto.FilterProductDTO;
import com.dotv.perfume.dto.ProductDTO;
import com.dotv.perfume.entity.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> getListNewProduct(int typeName,Boolean status);
    Product getProductById(int id);
    List<Product> getAllProductByBrandAndStatus(int id, Boolean status);
    int updateAmountProduct(int id, int amount);

    //filter product
    List<Product> getListProductByFilter(FilterProductDTO f);

    //admin
    //get all product or search
    List<Product> getListProduct(String search);
    Product saveOrUpdate(ProductDTO productDTO) throws IOException;
    void deleteProduct(int id);


}
