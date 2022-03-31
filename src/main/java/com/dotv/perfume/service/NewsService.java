package com.dotv.perfume.service;

import com.dotv.perfume.dto.NewsDTO;
import com.dotv.perfume.entity.News;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    List<News> getListNew(int status);
    News getNewsById(int id);
    News saveIntroduce(News news);
    News saveNews(NewsDTO newsDTO) throws IOException;
    void deleteNewsById(int id);
}
