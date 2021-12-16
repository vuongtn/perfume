package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.BillDetail;
import com.dotv.perfume.repository.BillDetailRepository;
import com.dotv.perfume.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public boolean saveBillDetail(BillDetail billDetail) {
        if(billDetailRepository.save(billDetail)!=null)
            return true;
        return false;
    }

    @Override
    public boolean saveAllBillDetail(List<BillDetail> billDetails) {
        if(billDetailRepository.saveAll(billDetails)!=null)
            return true;
        return false;
    }
}
