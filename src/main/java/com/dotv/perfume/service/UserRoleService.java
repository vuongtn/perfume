package com.dotv.perfume.service;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.entity.UserRole;

public interface UserRoleService {

    //Tạo tài khoản khách hàng
    UserRole saveUser(User user);

}
