package com.dotv.perfume.repository;

import com.dotv.perfume.dto.ProductInBillDTO;
import com.dotv.perfume.entity.BillDetail;
import com.dotv.perfume.entity.BillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillId> {
    @Query(name="ProductInBill", nativeQuery = true)
    List<ProductInBillDTO> getListProductInBill(int idBill);
}
