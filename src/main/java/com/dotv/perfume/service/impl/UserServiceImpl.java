package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.exception.EntityNotFoundCustomException;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByUsernameAndStatus(String username, Boolean status) {
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
}
