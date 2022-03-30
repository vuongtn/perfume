package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseAdminController;
import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Contact;
import com.dotv.perfume.service.ContactService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageAboutUsController extends BaseAdminController {
    @Autowired
    ContactService contactService;

    @GetMapping("/contact")
    public String getContact(){
        return "admin/contact/contact";
    }

    @GetMapping("/lst_contact")
    public ResponseEntity<List<Contact>> getListContact(@RequestParam int type){
        List<Contact> lst =contactService.getListContact(type).stream()
                .sorted(Comparator.nullsLast((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lst);
    }

    @PostMapping("/update_status_contact")
    public ResponseEntity<JSONObject> updateStatusContact(@RequestParam int id, @RequestParam Boolean status){
        JSONObject result = new JSONObject();
        try {
            PerfumeUtils perfumeUtils = new PerfumeUtils();
            contactService.updateStatusContact(id,status,getUserLogined().getFullName(),perfumeUtils.getDateNow());
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete_contact")
    public ResponseEntity<JSONObject> deleteContact(@RequestParam int id){
        JSONObject result = new JSONObject();
        try {
            contactService.deleteContact(id);
            result.put("message", Boolean.TRUE);
        }
        catch (Exception e){
            result.put("message", Boolean.FALSE);
        }
        return ResponseEntity.ok(result);
    }

}
