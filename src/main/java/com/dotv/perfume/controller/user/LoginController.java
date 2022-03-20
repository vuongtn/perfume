package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/")
public class LoginController extends BaseController {

    @GetMapping("/login.html")
    public String getLogin(@RequestParam(required = false) String type, @RequestParam(required = false) String login_error, Model model){
        model.addAttribute("login_error",login_error);
        model.addAttribute("type",type);
        return "user/login/login";
    }
    @GetMapping("/login_success")
    public String showPageSuccess() throws Exception {
        User user = getUserLogined();
        if ("GUEST".equals(user.getUserRoles().get(0).getRoleName())) {
            return "redirect:/";
        } else {
            return "redirect:/admin/brand";
        }
    }
}
