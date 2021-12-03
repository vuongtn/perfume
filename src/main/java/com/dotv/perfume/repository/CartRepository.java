package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Cart;
import com.dotv.perfume.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,CartId> {
}
