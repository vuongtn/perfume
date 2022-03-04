package com.dotv.perfume.repository;

import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
