package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
//    @Query(value = "select * from product p where p.id_category = :idCategory and p.status=true",nativeQuery = true)
//    Page<Product> getProductByIdCategory(@Param("idCategory") int idCategory, Pageable pageable);
//
//    @Query("select p from Product p where p.name like %?1%")
//    List<Product> getProductByName(String name);
//
//    @Query("select p from Product p where p.id=?1")
//    Product getProductById(int id);
    Page<Product> findAllByIdTrademarkAndStatus(int idTrademark, Boolean status, Pageable pageable);

    //Trả về list sản phẩm theo mã thương hiệu sắp xêp tăng dần theo giá
    List<Product> findAllByIdTrademarkAndStatusOrderByPriceAsc(int idTrademark, Boolean status, Pageable pageable);

    //Trả về list sản phẩm theo mã thương hiệu sắp xêp giảm dần theo giá
    List<Product> findAllByIdTrademarkAndStatusOrderByPriceDesc(int idTrademark, Boolean status, Pageable pageable);

    //Trả về list sản phẩm theo mã thương hiệu sắp xêp giảm dần theo tên
    List<Product> findAllByIdTrademarkAndStatusOrderByNameAsc(int idTrademark, Boolean status, Pageable pageable);

}
