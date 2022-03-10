package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Role;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.entity.UserRoleId;
import com.dotv.perfume.repository.RoleRepository;
import com.dotv.perfume.repository.UserRoleRepository;
import com.dotv.perfume.service.UserRoleService;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserService userService;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserRole saveUser(User user) {
        Timestamp timeNow = perfumeUtils.getDateNow();
        user.setCreatedDate(timeNow);
        user.setCreatedBy(user.getFullName());
        user.setStatus(true);
        user.setPassword(new BCryptPasswordEncoder().encode((user.getPassword())));
        //lưu user vào db
        userService.saveOrUpdate(user);
        //Tìm role là khách hàng
        Role role = roleRepository.findByCode("G");
        //Tạo id user_role
        UserRoleId userRoleId = new UserRoleId(user.getId(),role.getId());
        //Tạo user_role
        UserRole userRole = new UserRole(userRoleId,"GUEST",false,false,false,false,timeNow,true,user,role);
        //save userRole
        return userRoleRepository.save(userRole);
    }
}
