package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.BillDetail;
import com.dotv.perfume.entity.BillId;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.BillDetailRepository;
import com.dotv.perfume.repository.BillRepository;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public void saveBill(Bill bill) {
        //Lấy tất cả sản phẩm trong giỏ hàng theo idUser
        List<ProductInCartDTO> lstPro = cartService.getProductInCart(bill.getIdUser());
        double totalPrice=0;
        for(ProductInCartDTO pro1:lstPro){
            totalPrice = totalPrice + pro1.getAmount() * pro1.getPrice().doubleValue();
        }
        bill.setTotalPrice(BigDecimal.valueOf(totalPrice));
        billRepository.save(bill);
        for(ProductInCartDTO product:lstPro){
            BillDetail billDetail = new BillDetail();
            Product pro = productService.getProductById(product.getId());
            billDetail.setAmount(product.getAmount());
            billDetail.setCurrentlyPrice(product.getPrice());
            BillId billId = new BillId(product.getId(),bill.getId());
            billDetail.setId(billId);
            billDetail.setBill(bill);
            billDetail.setProduct(pro);
            billDetailRepository.save(billDetail);
        }
    }

    @Override
    public List<Bill> getBillByUser(int idUser) {
        return billRepository.findAllByIdUser(idUser);
    }

    @Override
    @Transactional
    public int updateSatatusBill(Bill bill) {
        return billRepository.updateStatusBill(bill.getStatus(),bill.getUpdatedBy(),bill.getUpdatedDate(),bill.getId());
    }

    @Override
    public List<Bill> getBillByStatus(int status) {
        return billRepository.findAllByStatus(status);
    }

    @Override
    public Double totalMoneyBillByWeek() {
        List<Bill> lst = billRepository.getListByWeek();
        double totalMoney=0;
        for(Bill bill:lst){
            totalMoney += bill.getTotalPrice().doubleValue();
        }
        return totalMoney;
    }

    @Override
    public Double totalMoneyBillByMonth() {
        List<Bill> lst = billRepository.getListByMonth();
        double totalMoney=0;
        for(Bill bill:lst){
            totalMoney += bill.getTotalPrice().doubleValue();
        }
        return totalMoney;
    }

    @Override
    public Double totalMoneyBillByYear() {
        List<Bill> lst = billRepository.getListByYear();
        double totalMoney=0;
        for(Bill bill:lst){
            totalMoney += bill.getTotalPrice().doubleValue();
        }
        return totalMoney;
    }

    @Override
    public List<Bill> totalBillMonth() {
        return billRepository.getListByMonth();
    }

    @Override
    public Double[] arrTotalMoneyMonths(String year) {
        Double[] arrTotalMoney = new Double[12];
        for(int i=1; i<=12; i++){
            String month=""+i;
            List<Bill> lst = billRepository.getListByMonthAndYear(month,year);
            double totalMoney=0;
            for(Bill bill:lst){
                totalMoney += bill.getTotalPrice().doubleValue();
            }
            arrTotalMoney[i-1]=totalMoney;
        }
        return arrTotalMoney;
    }

    @Override
    public List<Bill> lstYear() {
        return billRepository.getListYear();
    }


}
