package com.dotv.perfume.service;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Trademark;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TrademarkService {
    List<Trademark> getTrademarkByStatus(Boolean status);
    Trademark getTrademarkById(int id);
}

