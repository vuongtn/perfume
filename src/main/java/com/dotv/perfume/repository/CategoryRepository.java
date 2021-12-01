package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "select c from Category c where c.status=true")
    List<Category> getCategoryByStatus();
}
