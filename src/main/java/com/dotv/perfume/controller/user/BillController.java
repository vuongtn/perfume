package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.json.simple.JSONObject;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/per")
public class BillController extends BaseController {
    @Autowired
    CartService cartService;

    @Autowired
    BillService billService;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    UserRepository userRepository;

    /*
    * Trả về form thông tin đặt hàng và list sản phẩm trong giỏ hàng
    * */
    @GetMapping("/buy")
    public String getFormBuy(Model model) throws Exception {
        User user = getUserLogined();
        List<ProductInCartDTO> lstPro = cartService.getProductInCart(user.getId());
        if(lstPro.size()==0){
            return "redirect:/";
        }
        double totalPrice=0;
        for(ProductInCartDTO pro:lstPro){
            totalPrice = totalPrice + pro.getAmount() * pro.getPrice().doubleValue();
        }
        model.addAttribute("user",user);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("lstPro",lstPro);
        return "user/bill/bill";
    }


    /*
    * Submit form order lấy params save db
    * */
    @PostMapping("/checkout")
    public String processBuy(@ModelAttribute Bill bill) throws Exception {
        User user = getUserLogined();
//        if(userRepository.findByIdAndPhone(user.getId(),((String) params.get("phone")).trim()).size()!=0) {
//            model.addAttribute("errorUser", "Số điện thoại đã tồn tại!");
//            return "user/bill/bill";
//        }
        bill.setCreatedDate(perfumeUtils.getDateNow());
        bill.setStatus(1);
        bill.setIdUser(user.getId());
        billService.saveBill(bill);
        //Thành công chuyển hướng sang trang ql đơn hàng
        return "redirect:/per/order_acc?curPage=1&id="+bill.getId();
    }

    @PostMapping("/update-status-bill")
    public ResponseEntity<JSONObject> updateStatusBill(@RequestParam int id,@RequestParam int status){
        JSONObject result = new JSONObject();
        //status=1: đang xử lý
        //status=2: đang giao
        //status=3: đã giao
        //status=4: đã hủy
        try {
            Bill bill = new Bill();
            bill.setStatus(status);
            bill.setId(id);
            bill.setUpdatedDate(perfumeUtils.getDateNow());
            bill.setUpdatedBy("Khách hàng");
            billService.updateSatatusBill(bill);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }
}
