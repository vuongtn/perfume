package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
//
    //Trả về list sản phẩm theo thương hiệu status=true
    @Query("select p from Product p join p.brand b where b.id=?1 and b.status=true and p.status=?2 and p.amount>0")
    List<Product> getProductByTrademark(int id, boolean status);

    //Trả về tất cả sản phẩm có status = true
//    List<Product> findAllByStatus(boolean status, Pageable pageable);

    ///////////Thương hiệu/////////////////////////////////////////////

    //Trả về list sản phẩm theo mã thương hiệu sắp xếp tăng dần theo giá
//    @Query("select p from Product p join p.brand t where t.id=?1 and p.status=?2 order by p.price asc")
//    List<Product> getProductByTrademarkAscPrice(int id,boolean status,Pageable pageable);
//
//    //Trả về list sản phẩm theo mã thương hiệu sắp xếp giảm dần dần theo giá
//    @Query("select p from Product p join p.brand t where t.id=?1 and p.status=?2 order by p.price desc")
//    List<Product> getProductByTrademarkDescPrice(int id,boolean status,Pageable pageable);
//
//    //Trả về list sản phẩm theo mã thương hiệu sắp xêp tăng dần theo tên
//    @Query("select p from Product p join p.brand t where t.id=?1 and p.status=?2 order by p.name desc")
//    List<Product> getProductByTrademarkAscName(int id,boolean status,Pageable pageable);

    /////////////////////////////////////////////////////////////////////

    //Trả về list sản phẩm sắp xếp theo ngày gần nhất
    @Query("select p from Product p join p.brand b where b.status=true and p.status=?1 and p.amount>0 order by p.createdDate desc")
    List<Product> findAllByStatusOrderByCreatedDateDesc(Boolean status);

    //Trả về list sản phẩm theo giới tính
    @Query("select p from Product p join p.brand b where b.status=true and p.status=?1 and p.gender=?2 and p.amount>0")
    List<Product> findAllByStatusAndGender(Boolean status,String gender);

    ////////////////////////////Giới tính///////////////////////////////
    //Trả về list sản phẩm theo giới tính sắp xếp tăng dần theo giá
//    @Query("select p from Product p where p.gender=?1 and p.status=?2 order by p.price asc")
//    List<Product> getProductByGenderkAscPrice(String gender,boolean status,Pageable pageable);
//
//    //Trả về list sản phẩm theo giới tính sắp xếp giảm dần theo giá
//    @Query("select p from Product p where p.gender=?1 and p.status=?2 order by p.price desc")
//    List<Product> getProductByGenderkDescPrice(String gender,boolean status,Pageable pageable);
//
//    //Trả về list sản phẩm theo name sắp xếp tăng dần theo giá
//    @Query("select p from Product p where p.gender=?1 and p.status=?2 order by p.name asc")
//    List<Product> getProductByGenderkAscName(String gender,boolean status,Pageable pageable);

    ////////////////////////Tất cả////////////////////////////////////////////
    //Trả về list sản phẩm tăng dần theo giá
//    @Query("select p from Product p where p.status=?1 order by p.price asc")
//    List<Product> getProductAscPrice(boolean status,Pageable pageable);

    //Trả về list sản phẩm giảm dần theo giá
//    @Query("select p from Product p where p.status=?1 order by p.price desc")
//    List<Product> getProductDescPrice(boolean status,Pageable pageable);
//
//    //Trả về list sản phẩm theo name
//    @Query("select p from Product p where p.status=?1 order by p.name asc")
//    List<Product> getProductAscName(boolean status,Pageable pageable);

    /////////////
    //List<Product> findAllByStatus(boolean status);

    //update amount product
    @Modifying
    @Query("update Product p set p.amount=p.amount-?2 where p.id=?1")
    int updateAmount(int id, int amount);

    //Search sản phẩm theo tên sắp tăng dần theo tên
//    @Query("select p from Product p where p.status=?1 and lower(p.name) like lower(concat('%',?2,'%')) order by p.name asc ")
//    List<Product> getProductByName(boolean status, String name);

    @Query("select p from Product p where p.id=?1")
    Product findById(int id);

    //admin
    @Query("select p from Product p where lower(p.name) like lower(concat('%',?1,'%')) order by p.id desc ")
    List<Product> getProductBySearch(String search);
}
