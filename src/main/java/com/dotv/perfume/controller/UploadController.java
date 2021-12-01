package com.dotv.perfume.controller;

import com.dotv.perfume.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public String upload(@RequestParam("idProduct") int idProduct,@RequestParam("files") MultipartFile[] files) throws IOException {
        for(MultipartFile file:files){
            imageService.store(idProduct,file);
        }
        return "Thành công";
    }
}
