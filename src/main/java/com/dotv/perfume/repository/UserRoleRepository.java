package com.dotv.perfume.repository;

import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Modifying
    @Query(value = "delete from user_role where id_user=?1",nativeQuery = true)
    int deleteRoleByIdUser(int id);
}
