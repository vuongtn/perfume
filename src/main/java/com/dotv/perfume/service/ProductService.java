package com.dotv.perfume.service;
import com.dotv.perfume.entity.Product;
import java.util.List;


public interface ProductService {
    //Page<Product> getListProductByTrademark(int idTrademark, Boolean status, int curPage, int page);
    List<Product> getListProductByTrademark(int idTrademark, Boolean status, int typeFilter, int curPage, int page);
    List<Product> getListProductByGender(String gender, Boolean status, int typeFilter, int curPage, int page);
    List<Product> getListNewProduct(int typeName,Boolean status);
    Product getProductById(int id);
    List<Product> getAllProductByTrademark(int id, Boolean status);
    List<Product> getAllProductByStatus(Boolean status, int typeFilter, int curPage, int page);
    List<Product> getAllProduct(Boolean status);
}
