package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Product;
import com.dotv.perfume.entity.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrademarkRepository extends JpaRepository<Trademark,Integer> {
    @Query(value = "select c from Trademark c where c.status=:status")
    List<Trademark> getTrademarkByStatus(Boolean status);
//    int updateStatusBill(@Param("status") int status, @Param("idBill") int idBill);

}
