package com.dotv.perfume.service;

import com.dotv.perfume.entity.User;

import java.util.List;

public interface UserService {
    User getUserByUsernameAndStatus(String username, Boolean status);
    User getAdminByUsernameAndStatus(String username, Boolean status);
    User saveOrUpdate(User user);
    User getUserById(Integer id)  throws Exception;
    List<User> getAllEmployee(String roleName);
    void deleteUser(int id);
//    List<User> getUserByTypeAndUsername(String type,String username);
//    List<User> getUserByEmail(String email);
}
