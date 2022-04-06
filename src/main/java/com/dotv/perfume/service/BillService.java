package com.dotv.perfume.service;

import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.BillDetail;

import java.util.List;

public interface BillService {
    void saveBill(Bill bill);
    List<Bill> getBillByUser(int idUser);
    int updateSatatusBill(Bill bill);
    List<Bill> getBillByStatus(int status);

    //Thống kê
    Double totalMoneyBillByWeek();
    Double totalMoneyBillByMonth();
    Double totalMoneyBillByYear();
    List<Bill> totalBillMonth();
    Double[] arrTotalMoneyMonths(String year);
    List<Bill> lstYear();
}
