package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Account;
import com.dotv.perfume.repository.AccountRepository;
import com.dotv.perfume.service.AccountService;
import com.dotv.perfume.untils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String createAcc(Account account) {
        if(accountRepository.getUserName(account.getUserName())!=null)
            return "Tên đăng nhập đã tồn tại";
        if(accountRepository.getEmail(account.getEmail())!=null)
            return "Email đã tồn tại";
        accountRepository.save(account);
        return "Tạo tài khoản thành công";
    }

    @Override
    public Account getAccById(int id) {
        return accountRepository.getById(id);
    }

    @Override
    public String editAcc(Account account) {
        if(accountRepository.save(account)!=null)
            return "Cập nhật thành công";
        return "Cập nhật thất bại";
    }
}
