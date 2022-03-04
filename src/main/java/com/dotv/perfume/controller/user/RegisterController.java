package com.dotv.perfume.controller.user;

import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.service.impl.UserRoleService;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@Controller
public class RegisterController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/register")
    public String getFormRegister(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("userDTO",user);
        return "user/register/register";

    }
    @PostMapping("/verify_register")
    public String getVerifyRegister(UserDTO userDTO, Model model){
        model.addAttribute("check",1);

        return "user/register/register";

    }

    public int sendCodeMail(String emailReceiver,String fullname) throws IOException, MessagingException{
        Random random = new Random();
        Integer code = random.nextInt(900000) + 100000;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlMsg = "<div>Dear " + fullname + " ,</div> <br/>";
        htmlMsg += "<div>Cảm ơn bạn đã đăng ký thành viên tại <b>D.Perfume.</b></div> <br/>";
        htmlMsg += "<div>Mã xác thực đăng ký tài khoản của bạn là: <b>" + code + "</b></div><br/>";
        htmlMsg += "<div>Để bảo mật thông tin vui lòng không cung cấp mã trên cho bất kỳ ai.</div><br/>";
        htmlMsg += "<div>Cảm ơn!</div><br/>";
        htmlMsg += "<div style=\"color: red;\"><b>D.Perfume</b></div><br/>";

        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(emailReceiver);
        helper.setSubject("[D.Perfume] Xác thực tài khoản đăng ký.");

        return code;
    }

    //Gửi code xác thực đăng ký tài khoản
    @PostMapping("/send_code")
    public ResponseEntity<JSONObject> sendCode(HttpServletRequest request) throws IOException, MessagingException {
        String emailReceiver = request.getParameter("email");
        String fullname = request.getParameter("fullname");

        int code=sendCodeMail(emailReceiver,fullname);

        JSONObject result = new JSONObject();
        result.put("result", Boolean.TRUE);
        result.put("codeConfirm", code);
        return ResponseEntity.ok(result);
    }

//    @PostMapping ("/verify_code")
//    public ResponseEntity<JSONObject> verifyCode(HttpServletRequest request){
//        JSONObject result = new JSONObject();
//
//        int code = Integer.parseInt(request.getParameter("code"));
//        HttpSession session = request.getSession();
//        int codeSend = (int) session.getAttribute("codeVerify");
//        if(code==codeSend){
//            result.put("result", Boolean.TRUE);
//            //result.put("codeConfirm", code);
//        }
//        else{
//            result.put("result", Boolean.FALSE);
//        }
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/rigister_account")
    public ResponseEntity<JSONObject> registerAcc(@RequestBody UserDTO userDTO){
        JSONObject result = new JSONObject();
        try {
            User user = modelMapper.map(userDTO,User.class);
            userRoleService.saveUser(user);
            result.put("message", Boolean.TRUE);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
    }
}
