package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.service.UserRoleService;
import com.dotv.perfume.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${admin.page.employee}")
    String pageSizeEmployee;

    @Value("${admin.page.user}")
    String pageSizeUser;


    @GetMapping("/employee")
    @PreAuthorize("hasAuthority('ADMIN_ME')")
    public String getEmployee(Model model){
            model.addAttribute("pageSize",pageSizeEmployee);
            return "admin/employee/employee";
    }

    @PostMapping("/save_employee")
    @PreAuthorize("hasAuthority('ADMIN_ME')")
    public ResponseEntity<JSONObject> saveOrUpdateEmployee(@ModelAttribute UserDTO userDTO) {
        JSONObject result = new JSONObject();
        try{
            if(userDTO.getId()==null){
                if(userRepository.findAllByUsername(userDTO.getUsername()).size()!=0){
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
                if(userRepository.findByIdAndUsername(userDTO.getId(),userDTO.getUsername()).size()!=0){
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
    @PreAuthorize("hasAuthority('ADMIN_MU')")
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
    @PreAuthorize("hasAuthority('ADMIN_ME')")
    public ResponseEntity<JSONObject> deleteEmployee(@RequestParam int id){
        JSONObject result = new JSONObject();
        try {
            userService.deleteUser(id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN_MU')")
    public String getUser(Model model){
        model.addAttribute("pageSize",pageSizeUser);
        return "admin/user/user";
    }

    @PostMapping("/update_status_user")
    @PreAuthorize("hasAuthority('ADMIN_MU')")
    public ResponseEntity<JSONObject> updateStatusUser(@RequestParam int id, @RequestParam Boolean status){
        JSONObject result = new JSONObject();
        try {
            //PerfumeUtils perfumeUtils = new PerfumeUtils();
            userService.updateStatusUser(status,id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/account")
    public String getAccount(){
        return "admin/account/account";
    }

    //Trả về user đang đăng nhập
    @GetMapping("/cur_user")
    public ResponseEntity<User> getLoginUser() throws Exception {
        return ResponseEntity.ok(getUserLogined());
    }

    //Sửa thông tin tài khoản
    @PostMapping("/update_acc")
    public ResponseEntity<JSONObject> updateAccount(@ModelAttribute UserDTO userDTO){
        JSONObject result = new JSONObject();
        try {
            if(userRepository.findByIdAndPhone(userDTO.getId(),userDTO.getPhone()).size()>0){
                result.put("message", 1);
                return ResponseEntity.ok(result);
            }
            if(userRepository.findByIdAndEmail(userDTO.getId(),userDTO.getEmail()).size()>0){
                result.put("message", 2);
                return ResponseEntity.ok(result);
            }
            if(userService.updateAccount(userDTO)>0)
                result.put("message", 3);
            else
                result.put("message", 4);
        }
        catch (Exception e){
            result.put("message", 4);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update_pass")
    public ResponseEntity<JSONObject> getUpdatePassword(UserDTO userDTO) throws Exception {
        JSONObject result = new JSONObject();
        //BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        userDTO.setId(getUserLogined().getId());
        boolean isPass = bCryptPasswordEncoder.matches(userDTO.getOldPassword().trim(),getUserLogined().getPassword());//Kiểm tra pass có chính xác
        if(!isPass){
            result.put("message", 1);
        }
        else {
            userDTO.setPassword(bCryptPasswordEncoder.encode((userDTO.getPassword().trim())));
            if(userService.updateAccount(userDTO)>0)
                result.put("message", 2);
            else
                result.put("message", 3);
        }
        return ResponseEntity.ok(result);
    }
}
