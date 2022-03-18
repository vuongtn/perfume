package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.ProductInBillDTO;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.BillDetailRepository;
import com.dotv.perfume.repository.BillRepository;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.Pager;
import com.dotv.perfume.utils.PerfumeUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/")
public class AccountController extends BaseController {
    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 1;
    private static final int BUTTONS_TO_SHOW = 5;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BillRepository billRepository;

    @Autowired
    BillRepository useRepository;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    BillDetailRepository billDetailRepository;


    @GetMapping("/manage_acc")
    public String getAccManage(Model model) throws Exception {
        User user = getUserLogined();
        model.addAttribute("acc",user);
        return "user/account/manage_acc";
    }

    @GetMapping("/update_acc")
    public String getUpdateAcc(Model model) throws Exception {
        User user = getUserLogined();
        model.addAttribute("acc",user);
        return "user/account/update_acc";
    }

    @PostMapping("/update_account")
    public String updateAcc(User user, Model model) throws Exception {
        User userLogin = getUserLogined();
        model.addAttribute("acc",userLogin);
        if(userRepository.findByIdAndUsername(user.getId(),user.getUsername()).size()!=0){
            model.addAttribute("errorUser","Tên đăng nhập đã tồn tại!");
            model.addAttribute("acc",user);
            return "user/account/update_acc";
        }
        if(userRepository.findByIdAndPhone(user.getId(),user.getPhone()).size()!=0){
            model.addAttribute("errorUser","Số điện thoại đã tồn tại!");
            model.addAttribute("acc",user);
            return "user/account/update_acc";
        }

        User userBD = userService.getUserById(user.getId());
        user.setPhone(user.getPhone().trim());
        user.setStatus(userBD.getStatus());
        user.setCreatedBy(userBD.getCreatedBy());
        user.setUpdatedBy(userBD.getFullName());
        user.setCreatedDate(userBD.getCreatedDate());
        user.setUpdatedDate(perfumeUtils.getDateNow());
        user.setPassword(userBD.getPassword());
        user.setEmail(userBD.getEmail());

        userService.saveOrUpdate(user);
        model.addAttribute("type",1);
        model.addAttribute("acc", user);
        model.addAttribute("isLogined",true);
        model.addAttribute("userLogined",user);
        return "user/account/update_acc";
    }

    @GetMapping("/update_pass")
    public String getUpdatePass(Model model) throws Exception {
        User userLogin = getUserLogined();
        model.addAttribute("acc",userLogin);
        return "user/account/update_pass";
    }

    @PostMapping("/update_password")
    public String getUpdatePassword(UserDTO userDTO, Model model) throws Exception {
        User userLogined = getUserLogined();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPass = bcrypt.matches(userDTO.getOldPassword().trim(),userLogined.getPassword());//Kiểm tra pass có chính xác
        if(!isPass){
            model.addAttribute("errorUser","Mật khẩu cũ không chính xác!");
        }
        else {
            userLogined.setPassword(new BCryptPasswordEncoder().encode((userDTO.getPassword().trim())));
            userService.saveOrUpdate(userLogined);
            model.addAttribute("type", 1);
        }
        model.addAttribute("acc",userLogined);
        return "user/account/update_pass";
    }

    @GetMapping("/order_acc")
    public String getOrderAcc(@RequestParam int curPage, Model model) throws Exception {
        User user=userService.getUserById(getUserLogined().getId());

        //sắp xếp theo ngày mới nhất
        List<Bill> bills = user.getBills().stream()
                        .sorted(Comparator.nullsLast((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate())))
                        .skip((curPage-1)*PAGE).limit(PAGE).collect(Collectors.toList());


        int totalPage=(int)Math.ceil(user.getBills().size()/(float)PAGE);
        Pager pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("pager",pager);
        model.addAttribute("acc",user);
        model.addAttribute("bills",bills);
        return "user/account/order_acc";
    }

    @PostMapping("/product_in_bill")
    public ResponseEntity<List<ProductInBillDTO>> getProInBill(@RequestParam int idBill) throws Exception {
        return ResponseEntity.ok( billDetailRepository.getListProductInBill(idBill));
    }
}
