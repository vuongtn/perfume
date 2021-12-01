package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{
    @Query("select a from Account a where a.userName=?1")
    Account getUserName(String userName);
    @Query("select a from Account a where a.email=?1")
    Account getEmail(String email);

    @Query("select a from Account a where a.userName=?1")
    Account findByUsername(String userName);
}
