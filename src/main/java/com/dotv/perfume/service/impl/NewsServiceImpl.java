package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.News;
import com.dotv.perfume.repository.NewsRepository;
import com.dotv.perfume.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> getListNew(int status) {
        return newsRepository.findAllByStatusOrderByCreatedDateDesc(status);
    }

    @Override
    public News getNewsById(int id) {
        return newsRepository.getById(id);
    }

    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }
}
