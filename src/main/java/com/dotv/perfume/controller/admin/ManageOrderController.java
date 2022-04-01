package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.ProductInBillDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.repository.BillDetailRepository;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageOrderController extends BaseAdminController {
    @Autowired
    BillService billService;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    BillDetailRepository billDetailRepository;

    @GetMapping("/order")
    public String getOrder(){
        return "admin/order/order";
    }

    @GetMapping("/lst_order")
    public ResponseEntity<List<Bill>> getLstOrder(@RequestParam int status) {
        return ResponseEntity.ok(billService.getBillByStatus(status).stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate()))).collect(Collectors.toList()));
    }

    @PostMapping("/update_status_order")
    public ResponseEntity<JSONObject> updateStatus(@RequestParam int id, @RequestParam int status){
        JSONObject result = new JSONObject();
        try {
            Bill bill = new Bill();
            bill.setStatus(status);
            bill.setId(id);
            bill.setUpdatedDate(perfumeUtils.getDateNow());
            //bill.setUpdatedBy(getUserLogined().getFullName());
            billService.updateSatatusBill(bill);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/product_in_bill")
    public ResponseEntity<List<ProductInBillDTO>> getProInBill(@RequestParam int idBill) throws Exception {
        return ResponseEntity.ok( billDetailRepository.getListProductInBill(idBill));
    }
}
