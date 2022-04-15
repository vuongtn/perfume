package com.dotv.perfume.controller.user;

import com.dotv.perfume.config.RestFacebook;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
//@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private RestFacebook restFB;


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

    //login facebook
    @GetMapping("/login_facebook")
    public String loginFacebook(HttpServletRequest request) {
        String code = request.getParameter("code");
        String accessToken = "";
        try {
            accessToken = restFB.getToken(code);
        } catch (IOException e) {
            return "login?facebook=error";
        }
        com.restfb.types.User user = restFB.getUserInfo(accessToken);
        UserDetails userDetail = restFB.buildUser(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }


}
