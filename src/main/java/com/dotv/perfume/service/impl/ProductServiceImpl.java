package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Image;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.ProductRepository;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> getAllProduct(int currPage) {
        return productRepository.findAll(PageRequest.of(currPage - 1, 1, Sort.by("id").ascending()));
    }

    @Override
    public Page<Product> getAllByCategory(int id_category, int currPage) {
        return productRepository.getProductByIdCategory(id_category,PageRequest.of(currPage - 1, 1, Sort.by("id").ascending()));
        //return productRepository.getAllById(id_category);
    }

    @Override
    public Product getProductById(int idProduct) {
        return productRepository.getProductById(idProduct);
    }

    @Override
    public List<Product> getAllProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public Boolean addProduct(Product product, MultipartFile[] files) throws IOException {
        final String DIR_TO_UPLOAD="src\\main\\resources\\static\\uploads\\";
        if(productRepository.save(product)!=null) {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
                Files.write(path, bytes);
                //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            }
            return true;
        }
        return false;
    }
}
