package com.dotv.perfume.controller.admin;

import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.service.BillService;
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
public class ManageOrderController {
    @Autowired
    BillService billService;

    @GetMapping("/order")
    public String getOrder(){
        return "admin/order/order";
    }

    @GetMapping("/lst_order")
    public ResponseEntity<List<Bill>> getLstOrder(@RequestParam int status) {
        return ResponseEntity.ok(billService.getBillByStatus(status).stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate()))).collect(Collectors.toList()));
    }
}
