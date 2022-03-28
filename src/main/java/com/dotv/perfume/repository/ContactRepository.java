package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findAllByStatus(Boolean status);

    @Modifying
    @Query("update Contact c set c.status=?1, c.updatedBy=?2, c.updatedDate=?3 where c.id=?4")
    int updateStatus(Boolean status, String updatedBy, Timestamp updatedDate, int id);
}
