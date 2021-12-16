package com.dotv.perfume.service;

import com.dotv.perfume.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> getAllProduct(int currPage);
    Page<Product> getAllByCategory(int id_category, int currPage);
    Product getProductById(int idProduct);
    List<Product> getAllProductByName(String name);
    Boolean addProduct(Product product, MultipartFile[] files) throws IOException;
}
