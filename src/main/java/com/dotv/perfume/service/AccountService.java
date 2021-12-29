package com.dotv.perfume.service;

import com.dotv.perfume.entity.Account;

public interface AccountService {
    String createAcc(Account account);
    Account getAccById(int id);
    String editAcc(Account id);
    String editStatus(int id, int status);
    String deleteAcc(int id);
}
