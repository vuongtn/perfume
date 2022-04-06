package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
    User findByUsernameAndType(String username, String type);

    @Query("select u from User u where u.username=?1 and (u.type=?2 or u.type=?3)")
    User getAdminByUsernameAndType(String username, String type1, String type2);
    User findByUsername(String username);
    List<User> findAllByType(String roleName);
    List<User> findAllByUsername(String username);

    List<User> findByPhone(String phone);
    List<User> findByEmail(String email);

    @Query("select u from User u where u.id<>?1 and u.phone=?2")
    List<User> findByIdAndPhone(int id,String phone);
    @Query("select u from User u where u.id<>?1 and u.email=?2")
    List<User> findByIdAndEmail(int id,String email);
    @Query("select u from User u where u.id<>?1 and u.username=?2")
    List<User> findByIdAndUsername(int id, String username);


    @Query("select u from User u where u.type=?1 and lower(u.username) like lower(concat('%',?2,'%')) order by u.id asc")
    List<User> getUserByTypeAndSearch(String type,String search);

    @Modifying
    @Query("update User u set u.status=?1 where u.id=?2")
    int updateStatus(Boolean status, int id);

    @Modifying
    @Query("update User u set u.fullName=?1, u.phone=?2, u.email=?3 where u.id=?4")
    int updateAccount(String fullName, String phone, String email, int id);

    @Modifying
    @Query("update User u set u.password=?1 where u.id=?2")
    int updatePass(String password, int id);


}
