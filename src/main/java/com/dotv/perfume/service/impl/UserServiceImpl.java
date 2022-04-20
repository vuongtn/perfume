package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.exception.EntityNotFoundCustomException;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsernameAndType(String username, String type) {
        User user = userRepository.findByUsernameAndType(username,type);
        return user == null ? new User() : user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user == null ? new User() : user;
    }

    @Override
    public User getAdminByUsernameAndType(String username, String type1, String type2) {
        User user = userRepository.getAdminByUsernameAndType(username,type1, type2);
        return user == null ? new User() : user;
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundCustomException("Not found user"));
    }

    @Override
    public List<User> getAllEmployee(String roleName) {
        return userRepository.findAllByType(roleName);
    }

    @Override
    public List<User> getUserBySearch(String roleName, String search) {
        String query=perfumeUtils.convertToEnglish(search.trim());
        return userRepository.getUserByTypeAndSearch(roleName,search);
    }

    @Override
    public void deleteUser(int id) {
         userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int updateStatusUser(Boolean status, int id) {
        return userRepository.updateStatus(status,id);
    }

    @Override
    @Transactional
    public int updateAccount(UserDTO userDTO) {
        if(StringUtils.isBlank(userDTO.getPassword()))
            return userRepository.updateAccount(userDTO.getFullName(),userDTO.getPhone(),userDTO.getEmail(),userDTO.getId());
        return userRepository.updatePass(userDTO.getPassword(),userDTO.getId());

    }

    //send link update pass
    public boolean sendCodeMailAdmin(String emailReceiver,String fullname, String link) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlMsg = "<div>Xin chào " + fullname + ",</div> <br/>";
        htmlMsg += "<div>Bạn đã yêu cầu đặt lại mật khẩu tài khoản tại <b>D.Perfume.</b></div> <br/>";
        htmlMsg += "<div>Bạn vui lòng truy cập vào liên kết dưới đây để đặt lại mật khẩu.</div><br/>";
        htmlMsg += "<div><a href=\""+link+"\">Đặt lại mật khẩu</a></div><br/>";
        htmlMsg += "<div>Cảm ơn!</div><br/>";
        htmlMsg += "<div style=\"color: red;\"><b>D.Perfume</b></div><br/>";

        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(emailReceiver);
        helper.setSubject("[D.Perfume] Đặt lại mật khẩu.");
        try {
            emailSender.send(message);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean sendLinkUpdatePass(HttpServletRequest request,String email, int type) throws MessagingException {
        List<User> user = userRepository.findByEmail(email.trim());

        //type=1: forget pass admin
        //type=2: forget pass user
        if((type==1)&&(user.size()==0||user.get(0).getType().equals("GUEST"))||(type==2)&&(user.size()==0||!user.get(0).getType().equals("GUEST"))){
           return false;
        }
        else {
            User userUpdatePass=user.get(0);
            String token = UUID.randomUUID().toString();
            String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
            String link=siteURL + "/reset_password?token=" + token;
            if(sendCodeMailAdmin(email.trim(),userUpdatePass.getFullName(),link)){
                userUpdatePass.setToken(token);
                userUpdatePass.setExpiryDate(perfumeUtils.getDateNow());
                userRepository.save(userUpdatePass);
            }
            //userRepository.updatePass(bCryptPasswordEncoder.encode((passNew)),user.get(0).getId());
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updatePassToken(int idUser, String pass) {
        pass = bCryptPasswordEncoder.encode(pass);
        if(userRepository.updatePass(pass,idUser)!=0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkToken(String token) {
        User user = getUserByToken(token);
        if(user!=null){
            //sau 60 phút sẽ hết hạn token.
            if (perfumeUtils.calculateSecond(user.getExpiryDate())>(60*60)) {
                return false;
            }
            else
                return true;
        }
        return false;
    }

    @Override
    public User getUserByToken(String token) {
        return userRepository.findByToken(token);
    }

//    @Override
//    public List<User> getUserByTypeAndUsername(String type, String username) {
//        return userRepository.findAllByTypeAndUsername(type,username);
//    }
//
//    @Override
//    public List<User> getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
}
