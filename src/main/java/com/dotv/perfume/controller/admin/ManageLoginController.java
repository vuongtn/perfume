package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
public class ManageLoginController extends BaseAdminController {

    @Autowired
    UserService userService;


    int check=0;
    @GetMapping("/login_admin.html")
    public String getLoginAdmin(@RequestParam(required = false) String type, @RequestParam(required = false) String login_error,Model model){
        model.addAttribute("login_error",login_error);
        model.addAttribute("type",type);
        return "admin/login_admin";
    }

    @GetMapping("/check_login_admin")
    public String checkLoginAdmin(){
        if(check==1){
            check=0;
            return "redirect:/login_admin.html?type=2";
        }
        return "redirect:/login_admin.html";
    }

    @GetMapping("/login_admin_success")
    public String loginSuccess() throws Exception {
        User user = getUserLogined();
        if(user.getStatus()==false){
            check=1;
            return "redirect:/admin/logout_admin.html";
        }
        return "redirect:/admin/home";
    }


    //gửi link update pass về email
    @PostMapping("/send_link")
    public ResponseEntity<JSONObject> forgetPassword(HttpServletRequest request, @RequestParam String email, @RequestParam(required = false) int type) throws Exception {
        JSONObject result = new JSONObject();
        result.put("message",userService.sendLinkUpdatePass(request,email,type));
        return ResponseEntity.ok(result);
    }

    //kiểm tra token tồn tại thì show form
    @GetMapping("/reset_password")
    public String showFormUpdatePass(@RequestParam String token, Model model) {
        if(userService.checkToken(token)){
            User user = userService.getUserByToken(token);
            model.addAttribute("idUser",user.getId());
            return "admin/forget_pass";
        }
        return "redirect:/expire";
    }

    @PostMapping("/forget_pass")
    public ResponseEntity<JSONObject> forgetPassword(@RequestParam int idUser, @RequestParam String password) throws Exception {
        JSONObject result = new JSONObject();
        User user = userService.getUserById(idUser);
        if(user.getType().equals("GUEST")){
            result.put("typeUser",2);
        }
        else{
            result.put("typeUser",1);
        }
        result.put("message",userService.updatePassToken(idUser,password));
        return ResponseEntity.ok(result);
    }
}
