package com.dotv.perfume.service;

import com.dotv.perfume.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getListNew(int status);
    News getNewsById(int id);
}
