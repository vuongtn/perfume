package com.dotv.perfume.controller;

import com.dotv.perfume.dto.AccountDTO;
import com.dotv.perfume.entity.Account;
import com.dotv.perfume.service.AccountService;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
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
    public ResponseEntity<MessageResponse> createAcc(@Valid @RequestBody AccountDTO accountDTO){
        //accountDTO.setIdRole(1);
        //accountDTO.setStatus(true);
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setRole("ROLE_USER");
        //account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(true);
        MessageResponse message = new MessageResponse();
        message.setMessage(accountService.createAcc(account));
        return ResponseEntity.ok(message);
    }
    @GetMapping("/getAcc")
    public ResponseEntity<AccountDTO> getAccById(@RequestParam("id") int id){
        Account account = accountService.getAccById(id);
        AccountDTO accountDTO = modelMapper.map(account,AccountDTO.class);
        return ResponseEntity.ok(accountDTO);
    }
    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> editAcc(@Valid @RequestBody AccountDTO accountDTO){
        //accountDTO.setIdRole(1);
        //accountDTO.setStatus(true);
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setRole("ROLE_USER");
        account.setStatus(true);
        MessageResponse message = new MessageResponse();
        message.setMessage(accountService.editAcc(account));
        return ResponseEntity.ok(message);
    }

}
