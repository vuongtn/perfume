package com.dotv.perfume.service;

import com.dotv.perfume.entity.Brand;

import java.util.List;


public interface BrandService {
    List<Brand> getTrademarkByStatus(Boolean status);
    Brand getTrademarkById(int id);
}

