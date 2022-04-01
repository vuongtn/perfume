package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ManageLoginController extends BaseAdminController {

    int check=0;
    @GetMapping("/login_admin.html")
    public String getLoginAdmin(@RequestParam(required = false) String type, @RequestParam(required = false) String login_error,Model model){
        model.addAttribute("login_error",login_error);
        model.addAttribute("type",type);
        return "admin/login_admin";
    }

    @GetMapping("/check_login_admin")
    public String checkLogin(){
        if(check==1){
            return "redirect:/admin/login_admin.html?type=2";
        }
        return "redirect:/admin/login_admin.html";
    }

    @GetMapping("/login_success")
    public String loginSuccess() throws Exception {
        User user = getUserLogined();
        if(user.getStatus()==false){
            check=1;
            return "redirect:/admin/logout_admin.html";
        }
        if ("ADMIN_D".equals(user.getUserRoles().get(0).getRoleName())
        ||"ADMIN_S".equals(user.getUserRoles().get(0).getRoleName())) {
            return "redirect:/admin/brand";
        }
        return "admin/login_admin";
    }
}
