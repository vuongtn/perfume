package com.dotv.perfume.controller.user;

import com.dotv.perfume.config.GooglePojo;
import com.dotv.perfume.config.GoogleUtils;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private GoogleUtils googleUtils;

    @Value("${spring.social.google.clientId}")
    String clientId;
    @Value("${spring.social.google.clientSecret}")
    String clientSecret;

    int check=0;
    @GetMapping("/login.html")
    public String getLogin(@RequestParam(required = false) String type, @RequestParam(required = false) String login_error, Model model, HttpServletRequest request){
        model.addAttribute("login_error",login_error);
        model.addAttribute("type",type);
        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        String link="https://accounts.google.com/o/oauth2/auth?scope=openid+profile+email&redirect_uri="+siteURL+"/login_google&response_type=code&client_id="+clientId+"&approval_prompt=force";
        model.addAttribute("link",link);
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

    @GetMapping("/login_google")
    public String loginGoogle(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login.html?type=error_google";
        }
        String accessToken = googleUtils.getToken(code,request);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        User user = googleUtils.buildUser(googlePojo);
        UserDetails userDetail = user;
        if(!user.getType().equals("GUEST")) {
            return "redirect:/";
        }
        if(!user.getStatus()){
            return "redirect:/login.html?type=2";
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

}
