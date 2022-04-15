package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getProfileUrl());
        user.setUsername(connection.getDisplayName());
        connection.getProfileUrl();
        //user.setFullName(connection.fetchUserProfile().getFirstName()+" "+connection.fetchUserProfile().getLastName());
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        //user.setEmail(connection.fetchUserProfile().getEmail());
        user.setFullName(connection.getDisplayName());
        user.setEmail("abc");
        user.setType("GUEST");
        userRepository.save(user);
        return user.getUsername();
    }
}
