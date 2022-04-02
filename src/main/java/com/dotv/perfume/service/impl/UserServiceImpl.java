package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.exception.EntityNotFoundCustomException;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Override
    public User getUserByUsernameAndType(String username, String type) {
        User user = userRepository.findByUsernameAndType(username,type);
        return user == null ? new User() : user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user == null ? new User() : user;
    }

    @Override
    public User getAdminByUsernameAndType(String username, String type1, String type2) {
        User user = userRepository.getAdminByUsernameAndType(username,type1, type2);
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
    public List<User> getUserBySearch(String roleName, String search) {
        String query=perfumeUtils.convertToEnglish(search.trim());
        return userRepository.getUserByTypeAndSearch(roleName,search);
    }

    @Override
    public void deleteUser(int id) {
         userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int updateStatusUser(Boolean status, int id) {
        return userRepository.updateStatus(status,id);
    }

    @Override
    @Transactional
    public int updateAccount(UserDTO userDTO) {
        if(StringUtils.isBlank(userDTO.getPassword()))
            return userRepository.updateAccount(userDTO.getFullName(),userDTO.getPhone(),userDTO.getEmail(),userDTO.getId());
        return userRepository.updatePass(userDTO.getPassword(),userDTO.getId());

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
