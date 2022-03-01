package com.dotv.perfume.repository;

import com.dotv.perfume.entity.Introduce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntroductRepository extends JpaRepository<Introduce,Integer> {
    List<Introduce> findAllByStatus(Boolean status);
}
