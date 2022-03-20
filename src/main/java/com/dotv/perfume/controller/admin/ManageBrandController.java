package com.dotv.perfume.controller.admin;

import com.dotv.perfume.controller.BaseController;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ManageBrandController extends BaseController {
    //Số phần tử hiển thị 1 trang
    private static final int PAGE = 1;
    private static final int BUTTONS_TO_SHOW = 5;

    @Autowired
    BrandService brandService;

    @GetMapping("/brand")
    public String getListBrand(@RequestParam int curPage, Model model){
        List<Brand> lstBrand=brandService.getAllBrand();
        List<Brand> lstBrand1=brandService.getAllBrand().stream()
                        .sorted(Comparator.nullsLast((e1, e2) -> e2.getId().compareTo(e1.getId())))
                .skip((curPage-1)*PAGE).limit(PAGE).collect(Collectors.toList());

        model.addAttribute("lstBrand",lstBrand1);
        int totalPage=(int)Math.ceil(lstBrand.size()/(float)PAGE);
        Pager pager = new Pager(totalPage,curPage-1, BUTTONS_TO_SHOW);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("pager",pager);

        return "admin/brand/brand";
    }

    @PostMapping("/save_brand")
    public String saveBrand(Brand brand){
        brandService.saveOrUpdateBrand(brand);
        return "admin/brand/brand?curPage=1";
    }
//    @PostMapping("/update_brand")
}
