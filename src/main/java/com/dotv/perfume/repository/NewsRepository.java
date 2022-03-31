package com.dotv.perfume.repository;

import com.dotv.perfume.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
//    sắp xếp theo tín tức mới nhất
    List<News> findAllByStatusOrderByCreatedDateDesc(int status);

    //lấy tin tức khác giới thiệu (status!=0)
    @Query("select n from News n where n.status<>?1 order by n.createdDate desc ")
    List<News> findAllOrderByCreatedDateDesc(int status);

    News findById(int id);
}
