package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Trademark;
import com.dotv.perfume.repository.TrademarkRepository;
import com.dotv.perfume.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrademarkServiceImpl implements TrademarkService {
    @Autowired
    TrademarkRepository trademarkRepository;

    @Override
    public List<Trademark> getTrademarkByStatus(Boolean status) {
        return trademarkRepository.getTrademarkByStatus(true);
    }
}
