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
    @Query(value = "select * from product p where p.id_category = :idCategory and p.status=true",nativeQuery = true)
    Page<Product> getProductByIdCategory(@Param("idCategory") int idCategory, Pageable pageable);

    @Query("select p from Product p where p.name like %?1%")
    List<Product> getProductByName(String name);

}
