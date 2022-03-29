package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserRoleService;
import com.dotv.perfume.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManageEmployeeController extends BaseController {
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
    public ResponseEntity<List<User>> getLstEmployee(@RequestParam String type){
        return ResponseEntity.ok(userService.getAllEmployee(type));
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
}
