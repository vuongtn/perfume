package com.dotv.perfume.service;

import com.dotv.perfume.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;

public interface ImageService {
    Image store(int idProduct, MultipartFile file) throws IOException;
}
