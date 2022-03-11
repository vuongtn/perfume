package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.repository.TrademarkRepository;
import com.dotv.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    TrademarkRepository trademarkRepository;

    @Override
    public List<Brand> getTrademarkByStatus(Boolean status) {
        return trademarkRepository.getTrademarkByStatus(true);
    }

    @Override
    public Brand getTrademarkById(int id) {
        return trademarkRepository.getById(id);
    }
}
