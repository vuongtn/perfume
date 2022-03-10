package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.Bill;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.BillRepository;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
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

import java.util.List;

@Controller
//@RequestMapping("/")
public class AccountController extends BaseController {

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
        boolean isPass = bcrypt.matches(userDTO.getOldPassword(),userLogined.getPassword());//Kiểm tra pass có chính xác
        if(!isPass){
            model.addAttribute("errorUser","Mật khẩu cũ không chính xác!");
        }
        else {
            userLogined.setPassword(new BCryptPasswordEncoder().encode((userDTO.getPassword())));
            userService.saveOrUpdate(userLogined);
            model.addAttribute("type", 1);
        }
        model.addAttribute("acc",userLogined);
        return "user/account/update_pass";
    }

    @GetMapping("/order_acc")
    public String getOrderAcc(Model model) throws Exception {
        User userLogin = getUserLogined();
        model.addAttribute("acc",userLogin);
        return "user/account/order_acc";
    }

    @GetMapping("/getBill")
    public ResponseEntity<List<Bill>> getOrder() {
        //return ResponseEntity.ok();
        return null;
    }
}
