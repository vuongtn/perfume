package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrademarkRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "select c from Brand c where c.status=:status")
    List<Brand> getTrademarkByStatus(Boolean status);
//    int updateStatusBill(@Param("status") int status, @Param("idBill") int idBill);

}
