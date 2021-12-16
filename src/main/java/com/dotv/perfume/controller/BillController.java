package com.dotv.perfume.controller;

import com.dotv.perfume.dto.BillDTO;
import com.dotv.perfume.service.*;
import com.dotv.perfume.untils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    BillService billService;

    //Đặt hàng(lấy thông tin khách hàng lưu vào bill và sản phẩm lưu vào billDetail)
//    @PostMapping("client/order")
//    public MessageResponse Order(@RequestParam Map<String,Object> params){
//        Integer idAccount = Integer.valueOf((String)params.get("idAccount"));
//        String fullName = (String) params.get("fullName");
//        String address=(String) params.get("address");
//        String email=(String) params.get("email");
//        String phone=(String) params.get("phone");
//        String note=(String) params.get("note");
//        LocalDate createDate = LocalDate.now();
//        Bill bill = new Bill(idAccount,fullName,address,email,phone,note,1,createDate,createDate);
//
//        List<ProductInCartDTO> productInCart = cartService.getProductInCart(idAccount);
//        billService.Order(bill);
//        List<BillDetail> billDetails = new ArrayList<>();
//        for(ProductInCartDTO product:productInCart){
//            BillId billId = new BillId(product.getId(),bill.getId());
//
//            BillDetailDTO billDetail = new BillDetailDTO();
//            billDetail.setId(billId);
//            billDetail.setAmount(product.getAmount());
//            billDetail.setCurrentlyPrice(product.getPrice());
//
//            billDetails.add(modelMapper.map(billDetail,BillDetail.class));
//            //billDetailService.saveBillDetail(billDetail);
//        }
//        if(billDetailService.saveAllBillDetail(billDetails))
//            return new MessageResponse("Đặt hàng thành công");
//        else
//            return new MessageResponse("Đặt hàng thất bại");
//    }

    @PostMapping("client/order")
    public MessageResponse Order(@RequestBody BillDTO billDTO){
       return billService.Order(billDTO);
    }

    @GetMapping("/client/showBill")
    public List<BillDTO> getBillByStatus(@Param("idAcc") int idAcc, @Param("status") int status){
        return billService.getBillByStatus(idAcc,status);
    }
    @PutMapping("/client/updateStatus")
    public MessageResponse updateStatusBill(@Param("idBill") int idBill, @Param("status") int status){
        return billService.updateStatusBill(status,idBill);
    }
}
