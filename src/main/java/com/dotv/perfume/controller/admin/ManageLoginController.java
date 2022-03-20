package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManageLoginController extends BaseController {
    @GetMapping("/login_admin.html")
    public String getLoginAdmin(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getRequestURI());
//        model.addAttribute("login_error",login_error);
//        model.addAttribute("type",type);
            return "admin/login_admin";
    }
    @GetMapping("/admin/login_success")
    public String loginSuccess() throws Exception {
        User user = getUserLogined();
        if ("ADMIN_S".equals(user.getUserRoles().get(0).getRoleName())) {
            return "admin/brand/brand";
        }
        return "admin/login_admin";
    }
}
