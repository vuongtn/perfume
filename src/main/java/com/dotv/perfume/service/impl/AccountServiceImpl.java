package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Account;
import com.dotv.perfume.repository.AccountRepository;
import com.dotv.perfume.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public String editStatus(int id, int status) {
        int check=0;
        if(status==1) {
            check = accountRepository.editStatus(id, true);
        }
        else {
            check = accountRepository.editStatus(id, false);
        }
        if(check!=0 && status==1)
            return "Khôi phục tài khoản thành công";
        if(check!=0 && status ==0)
            return "Khóa tài khoản thành công";
        return "Không thành công";
    }

    @Override
    public String deleteAcc(int id) {
        accountRepository.deleteById(id);
        return "Xóa tài khoản thành công";
    }
}
