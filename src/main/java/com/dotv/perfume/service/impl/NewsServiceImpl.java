package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.NewsDTO;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.entity.News;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.NewsRepository;
import com.dotv.perfume.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ModelMapper modelMapper;

//    @Value("${upload.path}")
//    private String fileUpload;

    @Override
    public List<News> getListNew(int status) {
        if(status==-1){
          return newsRepository.findAllOrderByCreatedDateDesc(0);
        }
        return newsRepository.findAllByStatusOrderByCreatedDateDesc(status);
    }

    @Override
    public News getNewsById(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public News saveIntroduce(News news) {
        return newsRepository.save(news);
    }

    @Override
    @Transactional
    public News saveNews(NewsDTO newsDTO) throws IOException {
        if(newsDTO.getId()!=null) {
            News newsBD = getNewsById(newsDTO.getId());
            newsDTO.setCreatedBy(newsBD.getCreatedBy());
            newsDTO.setCreatedDate(newsBD.getCreatedDate());
        }
        News news = modelMapper.map(newsDTO,News.class);

        //Lưu file
        if(!newsDTO.getFileImage().isEmpty()) {
            news.setImage(newsDTO.getFileImage().getOriginalFilename());
            MultipartFile fileImage = newsDTO.getFileImage();
            //byte[] bytes = fileImage.getBytes();
            //Path path = Paths.get(System.getProperty("user.dir") + "/news/" + fileImage.getOriginalFilename());
            //Files.write(path, bytes);

            //new File(globalConfig.getUploadRootPath() + oldCategory.getAvatar()).delete();
            fileImage.transferTo(new File(System.getProperty("user.dir") + "/uploads/news/" + fileImage.getOriginalFilename()));
        }

        return newsRepository.save(news);
    }

    @Override
    @Transactional
    public void deleteNewsById(int id) {
        newsRepository.deleteById(id);
    }
}
