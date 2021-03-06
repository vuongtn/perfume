package com.dotv.perfume.controller.user;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.service.ContactService;
import com.dotv.perfume.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
//@RequestMapping("/")
public class AboutUsController extends BaseController {
    @Autowired
    NewsService newsService;
    @Autowired
    ContactService contactService;

    @GetMapping("/introduce")
    public String getIntroduce(Model model){
        model.addAttribute("typeMenu",5);
        if(newsService.getListNew(0).size()>0) {
            model.addAttribute("introduce", newsService.getListNew(0).get(0).getDetailDescription());
        }
        else{
            model.addAttribute("introduce","");
        }
        //model.addAttribute("introduce",newsService.getListNew(0));
        return "user/about_us/introduce";
    }

    @GetMapping("/contact")
    public String showFormContact(Model model){
        ContactDTO contac= new ContactDTO();
        model.addAttribute("contactDTO",contac);
        model.addAttribute("typeMenu",6);
        model.addAttribute("checkSuccess",1);
        return "user/about_us/contact";
    }

    @PostMapping("/add_contact")
    public String addContact(@Valid ContactDTO contactDTO, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("typeMenu",6);
            return "user/about_us/contact";
        }
        contactService.saveContact(contactDTO);
        model.addAttribute("checkSuccess",2);
        return "user/about_us/contact";
    }
}
