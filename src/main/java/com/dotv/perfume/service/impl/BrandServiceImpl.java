package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.repository.BrandRepository;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getBrandByStatus(Boolean status) {
        return brandRepository.getTrademarkByStatus(true);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.getAllBrand();
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.getBrandById(id);
    }

    @Override
    @Transactional
    public Brand saveOrUpdateBrand(Brand brand) {
        PerfumeUtils perfumeUtils = new PerfumeUtils();
        if(brand.getId()==null){
            brand.setCreatedDate(perfumeUtils.getDateNow());
//            brand.setCreatedBy(getUserLogined().getFullName());
        }
        else{
//            brand.setUpdatedBy(getUserLogined().getFullName());
            brand.setUpdatedDate(perfumeUtils.getDateNow());
        }
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }
}
