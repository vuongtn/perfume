package com.dotv.perfume.controller;

import com.dotv.perfume.entity.User;
import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class BaseAdminController {

    @Autowired
    private UserService userService;

    @ModelAttribute("isLoginedAdmin")
    public boolean isLogined() throws Exception {
        boolean isLogined = false;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if(getUserLogined().getType().equals("ADMIN_S")
            ||getUserLogined().getType().equals("ADMIN_D")) {
                isLogined = true;
            }
        }
        return isLogined;
    }

    @ModelAttribute("userLoginedAdmin")
    public User getUserLogined() throws Exception {
        Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userLogined != null && userLogined instanceof UserDetails) {
            User user = (User) userLogined;
            User userBD = userService.getUserById(user.getId());
            user.setStatus(userBD.getStatus());
            user.setCreatedBy(userBD.getCreatedBy());
            user.setUpdatedBy(userBD.getUpdatedBy());
            user.setCreatedDate(userBD.getCreatedDate());
            user.setUpdatedDate(userBD.getUpdatedDate());

            user.setUsername(userBD.getUsername());
            user.setPassword(userBD.getPassword());
            user.setEmail(userBD.getEmail());
            user.setFullName(userBD.getFullName());
            user.setAddress(userBD.getAddress());
            user.setPhone(userBD.getPhone());
            user.setPermission(userBD.getPermission());
            user.setType(userBD.getType());
            user.setToken(userBD.getToken());
            user.setExpiryDate(userBD.getExpiryDate());
            return user;
        }
        return null;
    }

    @ModelAttribute("brandRole")
    public Boolean getCategoryRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MB")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("productRole")
    public Boolean getProductRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MP")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("orderRole")
    public Boolean getOrderRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MO")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("newsRole")
    public Boolean getNewsRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MN")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("userRole")
    public Boolean getUserRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MU")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("employeeRole")
    public Boolean getEmployeeRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("ME")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("contactRole")
    public Boolean getContactRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MC")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("introduceRole")
    public Boolean getIntroduceRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MI")) {
                    return true;
                }
            }
        }
        return false;
    }

    @ModelAttribute("reportRole")
    public Boolean getReportRole() throws Exception {
        User user = getUserLogined();
        if (user != null) {
            for (UserRole userRole : user.getUserRoles()) {
                if (userRole.getRole().getCode().equalsIgnoreCase("MR")) {
                    return true;
                }
            }
        }
        return false;
    }


}
