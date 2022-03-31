package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserRoleService;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageUserController extends BaseAdminController {
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/employee")
    public String getEmployee(){
        return "admin/employee/employee";
    }

    @PostMapping("/save_employee")
    public ResponseEntity<JSONObject> saveOrUpdateEmployee(@ModelAttribute UserDTO userDTO) {
        JSONObject result = new JSONObject();
        try{
            if(userDTO.getId()==null){
                if(userRepository.findAllByTypeAndUsername("ADMIN_S",userDTO.getUsername()).size()!=0){
                    result.put("message", 3);
                    return ResponseEntity.ok(result);
                }
                if(userRepository.findByEmail(userDTO.getEmail()).size()!=0){
                    result.put("message", 4);
                    return ResponseEntity.ok(result);
                }
                if(userRepository.findByPhone(userDTO.getPhone()).size()!=0){
                    result.put("message", 5);
                    return ResponseEntity.ok(result);
                }
            }
            else {
                if(userRepository.findByIdAndUsernameAndType(userDTO.getId(),userDTO.getUsername(),"ADMIN_S").size()!=0){
                    result.put("message", 3);
                    return ResponseEntity.ok(result);
                }
                if(userRepository.findByIdAndEmail(userDTO.getId(),userDTO.getEmail()).size()!=0){
                    result.put("message", 4);
                    return ResponseEntity.ok(result);
                }
                if(userRepository.findByIdAndPhone(userDTO.getId(),userDTO.getPhone()).size()!=0){
                    result.put("message", 5);
                    return ResponseEntity.ok(result);
                }
            }
            userRoleService.saveEmployee(userDTO);
            result.put("message", 1);
            if(userDTO.getId()!=null){
                result.put("message", 2);
            }
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/lst_user")
    public ResponseEntity<List<User>> getLstEmployee(@RequestParam String type, @RequestParam(required = false) String search){
        if(StringUtils.isBlank(search)){
            return ResponseEntity.ok(userService.getAllEmployee(type).stream()
                    .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
                    .collect(Collectors.toList()));
        }
        else{
            return ResponseEntity.ok(userService.getUserBySearch(type,search.trim()));
        }

    }
    @PostMapping("/delete_user")
    public ResponseEntity<JSONObject> deleteEmployee(@RequestParam int id){
        JSONObject result = new JSONObject();
        try {
            userService.deleteUser(id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/user")
    public String getUser(){
        return "admin/user/user";
    }

    @PostMapping("/update_status_user")
    public ResponseEntity<JSONObject> updateStatusContact(@RequestParam int id, @RequestParam Boolean status){
        JSONObject result = new JSONObject();
        try {
            //PerfumeUtils perfumeUtils = new PerfumeUtils();
            userService.updateStatusUser(status,id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }

}
