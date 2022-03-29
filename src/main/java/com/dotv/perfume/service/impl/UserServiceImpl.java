package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.exception.EntityNotFoundCustomException;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByUsernameAndStatus(String username, Boolean status) {
        User user = userRepository.findByUsernameAndStatus(username,status);
        return user == null ? new User() : user;
    }

    @Override
    public User getAdminByUsernameAndStatus(String username, Boolean status) {
        User user = userRepository.findByUsernameAndStatus(username,status);
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
    public void deleteUser(int id) {
         userRepository.deleteById(id);
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
