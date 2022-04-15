package com.dotv.perfume.service;

import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User getUserByUsernameAndType(String username, String type);
    User getUserByUsername(String username);
    User getAdminByUsernameAndType(String username, String type1, String type2);
    User saveOrUpdate(User user);
    User getUserById(Integer id)  throws Exception;
    List<User> getAllEmployee(String roleName);
    List<User> getUserBySearch(String roleName,String search);
    void deleteUser(int id);
    int updateStatusUser(Boolean status, int id);
    int updateAccount(UserDTO userDTO);
    Boolean sendLinkUpdatePass(HttpServletRequest request, String email, int type) throws MessagingException;
    Boolean updatePassToken(int idUser, String pass);
    Boolean checkToken(String token);
    User getUserByToken(String token);
//    List<User> getUserByTypeAndUsername(String type,String username);
//    List<User> getUserByEmail(String email);
}
