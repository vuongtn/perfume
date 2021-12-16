package com.dotv.perfume.service;

import com.dotv.perfume.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    boolean saveBillDetail(BillDetail billDetail);
    boolean saveAllBillDetail(List<BillDetail> billDetails);
}
