package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender emailSender;

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

    //Lấy lại pass
    //send pass
    public int sendCodeMailAdmin(String emailReceiver,String fullname) throws IOException, MessagingException {
        Random random = new Random();
        Integer code = random.nextInt(900000) + 100000;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlMsg = "<div>Xin chào " + fullname + " ,</div> <br/>";
        htmlMsg += "<div>Bạn đã yêu cầu đặt lại mật khẩu tài khoản quản trị tại <b>D.Perfume.</b></div> <br/>";
        htmlMsg += "<div>Mật khẩu mới của bạn là: <b>" + code + "</b></div><br/>";
        htmlMsg += "<div>Vui lòng đổi lại mật khẩu và không cung cấp mã trên cho bất kỳ ai.</div><br/>";
        htmlMsg += "<div>Cảm ơn!</div><br/>";
        htmlMsg += "<div style=\"color: red;\"><b>D.Perfume</b></div><br/>";

        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(emailReceiver);
        helper.setSubject("[D.Perfume] Đặt lại mật khẩu.");
        emailSender.send(message);
        return code;
    }
//    public int sendCodeMail(String emailReceiver,String fullname) throws IOException, MessagingException {
//        Random random = new Random();
//        Integer code = random.nextInt(900000) + 100000;
//
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//        String htmlMsg = "<div>Xin chào " + fullname + " ,</div> <br/>";
//        htmlMsg += "<div>Bạn đã yêu cầu lấy lại mật khẩu tại <b>D.Perfume.</b></div> <br/>";
//        htmlMsg += "<div>Mật khẩu mới của bạn là: <b>" + code + "</b></div><br/>";
//        htmlMsg += "<div>Vui lòng đổi lại mật khẩu và không cung cấp mã trên cho bất kỳ ai.</div><br/>";
//        htmlMsg += "<div>Cảm ơn!</div><br/>";
//        htmlMsg += "<div style=\"color: red;\"><b>D.Perfume</b></div><br/>";
//
//        message.setContent(htmlMsg, "text/html; charset=UTF-8");
//        helper.setTo(emailReceiver);
//        helper.setSubject("[D.Perfume] Cấp lại mật khẩu cho khách hàng.");
//        emailSender.send(message);
//        return code;
//    }

    @PostMapping("/forget_pass")
    @Transactional
    public ResponseEntity<JSONObject> forgetPassword(@RequestParam String email, @RequestParam(required = false) int type) throws Exception {
        JSONObject result = new JSONObject();
        List<User> user = userRepository.findByEmail(email.trim());

        //type=1: forget pass admin
        //type=2: forget pass user
        if((type==1)&&(user.size()==0||user.get(0).getType().equals("GUEST"))||(type==2)&&(user.size()==0||!user.get(0).getType().equals("GUEST"))){
            result.put("message", Boolean.FALSE);
        }
        else {
            String passNew=String.valueOf(sendCodeMailAdmin(email.trim(),user.get(0).getFullName()));
            userRepository.updatePass(bCryptPasswordEncoder.encode((passNew)),user.get(0).getId());
            result.put("message", Boolean.TRUE);
        }
        return ResponseEntity.ok(result);
    }
}
