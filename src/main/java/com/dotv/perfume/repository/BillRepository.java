package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
//    List<Bill> findByIdAccountAndStatus(int idAcc, int status);

//    @Modifying
//    @Query("update Bill set status=:status where id=:idBill")
//    int updateStatusBill(@Param("status") int status, @Param("idBill") int idBill);

//    @Modifying
//    @Query("update Bill b set b.status=?1 where b.id=?2")
//    int updateStatusBill(int status,int idBill);

}
