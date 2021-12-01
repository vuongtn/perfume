package com.dotv.perfume.controller;

import com.dotv.perfume.dto.CategoryDTO;
import com.dotv.perfume.entity.Category;
import com.dotv.perfume.service.CategoryService;
import com.dotv.perfume.untils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

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
    public Message addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO,Category.class);
        Message message = new Message();
        if(categoryService.addCategory(category))
            message.setMessage("Thêm thành công");
        else
            message.setMessage("Thêm thất bại");
        return message;
    }


    //Trả vể category theo id
    @GetMapping ("/admin/category")
    public Optional<Category> editCategory(@RequestParam("id") int id){
       return categoryService.getCategoryById(id);
    }


    //Sửa category, input: name, status;
    @PostMapping("/admin/category/edit")
    public Message editCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO,Category.class);
        Message message = new Message();
        if(categoryService.addCategory(category))
            message.setMessage("Cập nhật thành công");
        else
            message.setMessage("Cập nhật thất bại");
        return message;
    }
}
