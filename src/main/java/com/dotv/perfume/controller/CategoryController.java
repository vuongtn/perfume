package com.dotv.perfume.controller;

import com.dotv.perfume.dto.CategoryDTO;
import com.dotv.perfume.entity.Category;
import com.dotv.perfume.service.CategoryService;
import com.dotv.perfume.untils.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /*Trả về danh sách danh mục có status = 1 (Hiển thị)*/
    @GetMapping("/client/category")
    public List<Category> getCategory1(){
//        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory().stream()
//                .map(category -> modelMapper.map(category,CategoryDTO.class))
//                .collect(Collectors.toList());
        return categoryService.getCategoryByStatus();
    }


    //phần admin hiển thị danh mục, thêm, sửa trang thái danh mục
    @GetMapping("/admin/categories")
    public List<Category> getAllCategory2(){
        return categoryService.getAllCategory();
    }


    //Thêm category, input: name, stauts;
    @PostMapping("/admin/category/add")
    public MessageResponse addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
      return categoryService.addCategory(categoryDTO);
    }


    //Trả vể category theo id
    //@GetMapping ("/admin/category")
    @PostMapping ("/admin/category")
    public Optional<Category> getCategoryById(@RequestParam("id") int id){
       return categoryService.getCategoryById(id);
    }


    //Sửa category, input: id, name, status;
    @PutMapping("/admin/category/edit")
    public MessageResponse editCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }


}
