package com.dotv.perfume.repository;

import com.dotv.perfume.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
//    @Query("select a from Account a where a.userName=?1")
//    Account getUserName(String userName);
//    @Query("select a from Account a where a.email=?1")
//    Account getEmail(String email);
//
//    @Query("select a from Account a where a.userName=?1")
//    Account findByUsername(String userName);
//
//    @Modifying
//    @Query("update Account a set a.status=?2 where a.id=?1")
//    int editStatus(int id, boolean status);
    User findByUsernameAndStatus(String username, Boolean status);
    List<User> findAllByType(String roleName);
    List<User> findByUsername(String username);

    List<User> findByPhone(String phone);
    List<User> findByEmail(String email);
    List<User> findAllByTypeAndUsername(String type, String username);

    @Query("select u from User u where u.id<>?1 and u.phone=?2")
    List<User> findByIdAndPhone(int id,String phone);
    @Query("select u from User u where u.id<>?1 and u.email=?2")
    List<User> findByIdAndEmail(int id,String email);
    @Query("select u from User u where u.id<>?1 and u.username=?2 and u.type=?3")
    List<User> findByIdAndUsernameAndType(int id, String username, String type);

}
