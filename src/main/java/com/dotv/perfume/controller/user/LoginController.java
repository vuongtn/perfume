package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/")
public class LoginController extends BaseController {

    int check=0;
    @GetMapping("/login.html")
    public String getLogin(@RequestParam(required = false) String type, @RequestParam(required = false) String login_error, Model model){
        model.addAttribute("login_error",login_error);
        model.addAttribute("type",type);
        return "user/login/login";
    }

    @GetMapping("/check_login")
    public String checkLogin(){
        if(check==1){
            check=0;
            return "redirect:/login.html?type=2";
        }
        return "redirect:/login.html";
    }

    @GetMapping("/login_success")
    public String showPageSuccess() throws Exception {
        User user = getUserLogined();
        if(user.getStatus()==false){
            check=1;
            return "redirect:/logout.html";
        }
        if ("GUEST".equals(user.getUserRoles().get(0).getRoleName())) {
            return "redirect:/";
        }
       return "redirect:/logout.html";
    }
}
