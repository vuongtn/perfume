package com.dotv.perfume.controller;

import com.dotv.perfume.dto.AccountDTO;
import com.dotv.perfume.entity.Account;
import com.dotv.perfume.service.AccountService;
import com.dotv.perfume.untils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    /*
    * Đăng kí acc khách hàng: userName, pass, fullName, emal
    * */
    @PostMapping("/register")
    public ResponseEntity<Message> createAcc(@Valid @RequestBody AccountDTO accountDTO){
        //accountDTO.setIdRole(1);
        //accountDTO.setStatus(true);
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setRole("ROLE_USER");
        //account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(true);
        Message message = new Message();
        message.setMessage(accountService.createAcc(account));
        return ResponseEntity.ok(message);
    }
    @GetMapping("/getAcc")
    public ResponseEntity<AccountDTO> getAccById(@Valid @RequestParam("id") int id){
        Account account = accountService.getAccById(id);
        AccountDTO accountDTO = modelMapper.map(account,AccountDTO.class);
        return ResponseEntity.ok(accountDTO);
    }
    @PostMapping("/edit")
    public ResponseEntity<Message> editAcc(@Valid @RequestBody AccountDTO accountDTO){
        //accountDTO.setIdRole(1);
        //accountDTO.setStatus(true);
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setRole("ROLE_USER");
        account.setStatus(true);
        Message message = new Message();
        message.setMessage(accountService.editAcc(account));
        return ResponseEntity.ok(message);
    }

}
