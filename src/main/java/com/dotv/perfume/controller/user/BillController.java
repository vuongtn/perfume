package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.OrderDTO;
import com.dotv.perfume.dto.ProductInCartDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.service.BillService;
import com.dotv.perfume.service.CartService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/")
public class BillController extends BaseController {
    @Autowired
    CartService cartService;

    @Autowired
    BillService billService;

    @Autowired
    PerfumeUtils perfumeUtils;

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

    @PostMapping("/checkout")
    public String processBuy(@RequestParam Map<String,Object> params, Model model) throws Exception {
        Bill bill = new Bill();
        bill.setReceiverName((String)params.get("fullName"));
        bill.setReceiverEmail((String)params.get("email"));
        bill.setReceiverPhone((String)params.get("phone"));
        bill.setReceiverAddress((String)params.get("address"));
        bill.setNote((String)params.get("note"));
        bill.setPayment((String)params.get("payment"));
        bill.setCreatedDate(perfumeUtils.getDateNow());
        bill.setStatus(1);
        bill.setUser(getUserLogined());
        billService.saveBill(bill);
        return "user/bill/bill";
    }
}
