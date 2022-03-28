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
        List<ProductInCartDTO> lstPro = cartService.getProductInCart(bill.getUser().getId());
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
        return null;
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
}
