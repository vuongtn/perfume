package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,CartId> {
//    @Query(value = "select p.id, p.name, p.price, c.amount, i.name as nameImg from cart c " +
//            "join product p on c.id_product = p.id " +
//            "join image i on p.id=i.id_product " +
//            "and id_account=?1 and i.type='main'", nativeQuery = true)
//    @Query(name = "ProductTest",nativeQuery = true)
//    List<ProductInCartDTO> getProductInCart(int idAccount);
}
