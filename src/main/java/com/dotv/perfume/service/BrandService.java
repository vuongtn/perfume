package com.dotv.perfume.service;

import com.dotv.perfume.entity.Brand;

import java.util.List;


public interface BrandService {
    List<Brand> getTrademarkByStatus(Boolean status);
    List<Brand> getAllBrand();
    Brand getTrademarkById(int id);
    Brand saveOrUpdateBrand(Brand brand);
    void deleteBrand(int id);
}

