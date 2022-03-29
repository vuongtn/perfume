package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.UserDTO;
import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.service.UserRoleService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageEmployeeController extends BaseController {
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/employee")
    public String getEmployee(){
        return "admin/employee/employee";
    }

    @PostMapping("/save_employee")
    public ResponseEntity<JSONObject> saveOrUpdateEmployee(@ModelAttribute UserDTO userDTO) {
        JSONObject result = new JSONObject();
        try{
            userRoleService.saveEmployee(userDTO);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }
}
