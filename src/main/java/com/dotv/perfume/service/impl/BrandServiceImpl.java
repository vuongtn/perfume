package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.repository.BrandRepository;
import com.dotv.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getTrademarkByStatus(Boolean status) {
        return brandRepository.getTrademarkByStatus(true);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.getAllBrand();
    }

    @Override
    public Brand getTrademarkById(int id) {
        return brandRepository.getById(id);
    }

    @Override
    public Brand saveOrUpdateBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
