package com.dotv.perfume.service;

import com.dotv.perfume.entity.User;

public interface UserService {
    User getUserByUsernameAndStatus(String username, Boolean status);
    User saveOrUpdate(User user);
}
