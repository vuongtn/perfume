package com.dotv.perfume.service;
import com.dotv.perfume.entity.Product;
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

    //admin
    //get all product or search
    List<Product> getListProduct(String search);

}
