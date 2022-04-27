package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class ManageDashBoardController extends BaseAdminController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;

    @GetMapping("/home")
    public String getHome(){
        return "admin/home/home";
    }

    @GetMapping("/dash")
    @PreAuthorize("hasAuthority('ADMIN_MR')")
    public String getDash(){
        return "admin/dash/dash";
    }

    @GetMapping("/get_dash")
    @PreAuthorize("hasAuthority('ADMIN_MR')")
    public ResponseEntity<JSONObject> getReport(@RequestParam(required = false) String year){
        JSONObject result = new JSONObject();
        result.put("totalUser",userService.getAllEmployee("GUEST").size());
        result.put("totalProduct",productService.getListProduct("all").size());
        result.put("totalBillMonth",billService.totalBillMonth().size());
        result.put("moneyWeek",billService.totalMoneyBillByWeek());
        result.put("moneyMonth",billService.totalMoneyBillByMonth());
        result.put("moneyYear",billService.totalMoneyBillByYear());
        result.put("arrTotalMoney",billService.arrTotalMoneyMonths(year));
        result.put("lstYear",billService.lstYear());
        return ResponseEntity.ok(result);
    }

}
