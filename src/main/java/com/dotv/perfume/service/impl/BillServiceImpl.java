package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.BillDTO;
import com.dotv.perfume.dto.BillDetailDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.BillDetail;
import com.dotv.perfume.entity.BillId;
import com.dotv.perfume.repository.BillDetailRepository;
import com.dotv.perfume.repository.BillRepository;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CartService cartService;

    @Override
    public MessageResponse Order(BillDTO billDTO) {
        LocalDate createDate = LocalDate.now();
        Bill bill = modelMapper.map(billDTO,Bill.class);
        bill.setStatus(1);
        bill.setCreateDate(createDate);
        bill.setModifyDate(createDate);

        List<ProductInCartDTO> productInCart = cartService.getProductInCart(bill.getIdAccount());
        billRepository.save(bill);
        List<BillDetail> billDetails = new ArrayList<>();
        for(ProductInCartDTO product:productInCart){
            BillId billId = new BillId(product.getId(),bill.getId());

            BillDetailDTO billDetail = new BillDetailDTO();
            billDetail.setId(billId);
            billDetail.setAmount(product.getAmount());
            billDetail.setCurrentlyPrice(product.getPrice());

            billDetails.add(modelMapper.map(billDetail,BillDetail.class));
            //billDetailService.saveBillDetail(billDetail);
        }
       if(billDetailRepository.saveAll(billDetails)!=null)
           return new MessageResponse("Đặt hàng thành công");
       return new MessageResponse("Đặt hàng thất bại");
    }

    @Override
    public List<BillDTO> getBillByStatus(int idAcc, int status) {
        List<BillDTO> billDTOs = billRepository.findByIdAccountAndStatus(idAcc,status).stream()
                .map(bill -> modelMapper.map(bill,BillDTO.class)).collect(Collectors.toList());
        return billDTOs;
    }

    @Override
    @Transactional
    public MessageResponse updateStatusBill(int status, int idBill) {
        if(billRepository.updateStatusBill(status,idBill)!=0)
            return new MessageResponse("Thành công");
        return new MessageResponse("Thất bại");
    }

}
