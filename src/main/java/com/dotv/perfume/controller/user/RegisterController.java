package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserRoleService;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

@Controller
public class RegisterController extends BaseController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRepository userRepository;

    @Value("${spring.social.google.clientId}")
    String clientId;
    @Value("${spring.social.google.clientSecret}")
    String clientSecret;

    @GetMapping("/register")
    public String getFormRegister(Model model, HttpServletRequest request){
        UserDTO user = new UserDTO();
        model.addAttribute("userDTO",user);
        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        String link="https://accounts.google.com/o/oauth2/auth?scope=openid+profile+email&redirect_uri="+siteURL+"/login_google&response_type=code&client_id="+clientId+"&approval_prompt=force";
        model.addAttribute("link",link);
        return "user/register/register";

    }
//    @PostMapping("/verify_register")
//    public String getVerifyRegister(UserDTO userDTO, Model model){
//        model.addAttribute("pass",userDTO.getPassword());
//        model.addAttribute("confirmPass",userDTO.getConfirmPassword());
//        if(userRepository.findByEmail(userDTO.getEmail().trim()).size()!=0){
//            model.addAttribute("errorUser","Email ???? t???n t???i");
//            return "user/register/register";
//        }
//        if(userRepository.findAllByUsername(userDTO.getUsername().trim()).size()!=0){
//            model.addAttribute("errorUser","T??n ????ng nh???p ???? t???n t???i");
//            return "user/register/register";
//        }
//        try {
//            int code = sendCodeMail(userDTO.getEmail(), userDTO.getFullName());
//            model.addAttribute("codeSend",code);
//        }
//        catch (Exception ex){
//            model.addAttribute("errorUser","L???i h??? th???ng.");
//            return "user/register/register";
//        }
//        model.addAttribute("check",1);
////        model.addAttribute("pass",userDTO.getPassword());
////        model.addAttribute("confirmPass",userDTO.getConfirmPassword());
//        return "user/register/register";
//
//    }

    @PostMapping("/verify_register")
    public ResponseEntity<JSONObject> getVerifyRegister(@ModelAttribute UserDTO userDTO){
        JSONObject result = new JSONObject();
        userDTO.setUsername(userDTO.getEmail());
        if(userRepository.findByEmail(userDTO.getEmail().trim()).size()!=0){
            result.put("message",1);
            return ResponseEntity.ok(result);
        }
//        if(userRepository.findAllByUsername(userDTO.getUsername().trim()).size()!=0){
//            result.put("message",2);
//            return ResponseEntity.ok(result);
//        }
        try {
            int code = sendCodeMail(userDTO.getEmail(), userDTO.getFullName());
            result.put("codeSend",code);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex){
            result.put("message",3);
            return ResponseEntity.ok(result);
        }
    }
    public int sendCodeMail(String emailReceiver,String fullname) throws IOException, MessagingException{
        Random random = new Random();
        Integer code = random.nextInt(900000) + 100000;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlMsg = "<div>Dear " + fullname + " ,</div> <br/>";
        htmlMsg += "<div>C???m ??n b???n ???? ????ng k?? th??nh vi??n t???i <b>D.Perfume.</b></div> <br/>";
        htmlMsg += "<div>M?? x??c th???c ????ng k?? t??i kho???n c???a b???n l??: <b>" + code + "</b></div><br/>";
        htmlMsg += "<div>????? b???o m???t th??ng tin vui l??ng kh??ng cung c???p m?? tr??n cho b???t k??? ai.</div><br/>";
        htmlMsg += "<div>C???m ??n!</div><br/>";
        htmlMsg += "<div style=\"color: red;\"><b>D.Perfume</b></div><br/>";

        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(emailReceiver);
        helper.setSubject("[D.Perfume] X??c th???c t??i kho???n ????ng k??.");
        emailSender.send(message);
        return code;
    }

    //G???i code x??c th???c ????ng k?? t??i kho???n
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

    @PostMapping("/register_account")
    public ResponseEntity<JSONObject> registerAcc(@RequestBody UserDTO userDTO){
        JSONObject result = new JSONObject();
        userDTO.setUsername(userDTO.getEmail());
        if(userRepository.findByEmail(userDTO.getEmail()).size()!=0||userRepository.findAllByUsername(userDTO.getUsername()).size()!=0){
            result.put("message",Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
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
