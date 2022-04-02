package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userService.getAdminByUsernameAndType(username, "ADMIN_S","ADMIN_D");
            //return userService.getUserByUsernameAndType(username,"ADMIN_D");
        } catch (Exception e) {
            return new User();
        }
    }
}
