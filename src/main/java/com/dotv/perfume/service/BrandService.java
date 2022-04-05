package com.dotv.perfume.service;

import com.dotv.perfume.entity.Brand;

import java.util.List;


public interface BrandService {
    List<Brand> getBrandByStatus(Boolean status);
    List<Brand> getAllBrand();
    Brand getBrandById(int id);
    Brand saveOrUpdateBrand(Brand brand);
    void deleteBrand(int id);
}

