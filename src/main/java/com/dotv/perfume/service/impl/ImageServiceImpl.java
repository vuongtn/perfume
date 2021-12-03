package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Image;
import com.dotv.perfume.repository.ImageRepository;
import com.dotv.perfume.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;


    @Override
    public Image store(int idProduct, MultipartFile file) throws IOException {
        final String DIR_TO_UPLOAD="src\\main\\resources\\static\\uploads\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
        Files.write(path, bytes);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();
        image.setName(fileName);
        //image.setIdProduct(idProduct);
        return imageRepository.save(image);
    }
}
