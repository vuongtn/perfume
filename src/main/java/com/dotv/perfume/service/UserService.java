package com.dotv.perfume.service;

import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;

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

//    List<User> getUserByTypeAndUsername(String type,String username);
//    List<User> getUserByEmail(String email);
}
