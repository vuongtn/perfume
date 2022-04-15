package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
//    List<Bill> findByIdAccountAndStatus(int idAcc, int status);

//    @Modifying
//    @Query("update Bill set status=:status where id=:idBill")
//    int updateStatusBill(@Param("status") int status, @Param("idBill") int idBill);

    @Modifying
    @Query("update Bill b set b.status=?1, b.updatedBy=?2, b.updatedDate=?3 where b.id=?4")
    int updateStatusBill(int status,String updateBy,java.sql.Timestamp updatedDate, int idBill);

    List<Bill> findAllByStatus(int status);

    List<Bill> findAllByIdUser(int idUser);

    //Thống kê
    @Query(value = "select * from bill where week(updated_date) = week(curdate()) and month(updated_date) = month(curdate()) and year(updated_date) = year(curdate()) and status=3",nativeQuery = true)
    List<Bill> getListByWeek();
    @Query(value = "select * from bill where month(updated_date) = month(curdate()) and year(updated_date) = year(curdate()) and status=3",nativeQuery = true)
    List<Bill> getListByMonth();
    @Query(value = "select * from bill where year(updated_date) = year(curdate()) and status=3",nativeQuery = true)
    List<Bill> getListByYear();

    //
    @Query(value = "select * from bill where month(updated_date) =?1 and year(updated_date)=?2 and status=3",nativeQuery = true)
    List<Bill> getListByMonthAndYear(String month, String year);

    @Query(value = "select * from bill where status=3 group by year(updated_date) order by(updated_date) desc",nativeQuery = true)
    List<Bill> getListYear();
}
