package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.FilterProductAdminDTO;
import com.dotv.perfume.dto.FilterProductDTO;
import com.dotv.perfume.dto.ProductDTO;
import com.dotv.perfume.entity.Brand;
import com.dotv.perfume.entity.Product;
import com.dotv.perfume.repository.ProductRepository;
import com.dotv.perfume.service.BrandService;
import com.dotv.perfume.service.ProductService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PerfumeUtils perfumeUtils;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BrandService brandService;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Override
    public List<Product> getListNewProduct(int typeName,Boolean status) {
        //get list sản phẩm sắp theo ngày
        if(typeName==1) {
            return productRepository.findAllByStatusOrderByCreatedDateDesc(status);
        }
        //get list sản phẩm nam
        if(typeName==2) {
            return productRepository.findAllByStatusAndGender(true,"Nam");
        }
        //get list sản phẩm nữ
        if(typeName==3) {
            return productRepository.findAllByStatusAndGender(true,"Nữ");
        }
        //get list sản phẩm unisex
        if(typeName==4){
            return productRepository.findAllByStatusAndGender(true,"Unisex");
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductByBrandAndStatus(int id, Boolean status) {
        return productRepository.getProductByTrademark(id,status);
    }

    @Override
    @Transactional
    public int updateAmountProduct(int id, int amount) {
        return productRepository.updateAmount(id,amount);
    }


    public StringBuilder getFilterPrice(StringBuilder sqlBuilder, FilterProductDTO f){
        if(ArrayUtils.isNotEmpty(f.getPrices())){
            sqlBuilder.append("and(  ");
            for(int i=0; i<f.getPrices().length; i++){
                if(i!=0){
                    sqlBuilder.append("or  ");
                }
                switch (f.getPrices()[i]) {
                    case 1:
                        sqlBuilder.append("p.price < 500000  ");
                        break;
                    case 2:
                        sqlBuilder.append("p.price >= 500000 and p.price <=1000000  ");
                        break;
                    case 3:
                        sqlBuilder.append("p.price >= 1000000 and p.price <=1500000  ");
                        break;
                    case 4:
                        sqlBuilder.append("p.price >= 1500000 and p.price <=2000000  ");
                        break;
                    case 5:
                        sqlBuilder.append("p.price >= 2000000 and p.price <=2500000  ");
                        break;
                    case 6:
                        sqlBuilder.append("p.price > 2500000  ");
                        break;
                }
            }
            sqlBuilder.append(")  ");
        }
        return sqlBuilder;
    }

    public StringBuilder getFilterSort(StringBuilder sqlBuilder, FilterProductDTO f) {
        if(StringUtils.isNotBlank(f.getSortBy())){
            switch (f.getSortBy()) {
                case "name":
                    sqlBuilder.append("order by p.name asc  ");
                    break;
                case "price1":
                    sqlBuilder.append("order by p.price asc  ");
                    break;
                case "price2":
                    sqlBuilder.append("order by p.price desc  ");
                    break;
            }
        }
        return sqlBuilder;
    }

    public StringBuilder getFilterBrand(StringBuilder sqlBuilder, FilterProductDTO f, Map<String, Object> parameters) {
        if(ArrayUtils.isNotEmpty(f.getBrands())){
            sqlBuilder.append("and(  ");
           for(int i=0; i<f.getBrands().length; i++){
               if(i!=0){
                   sqlBuilder.append("or  ");
               }
               sqlBuilder.append("p.id_brand=:idBrand"+i+"  ");
               parameters.put("idBrand"+i, f.getBrands()[i]);
           }
            sqlBuilder.append(")  ");
        }
        return sqlBuilder;
    }


    @Override
    public List<Product> getListProductByFilter(FilterProductDTO f) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select p.id id, p.name name, p.price, p.image image  ");
        sqlBuilder.append("from product p inner join brand b on p.id_brand= b.id  ");
        sqlBuilder.append("where 1=1 and b.status=true and p.status=true and p.amount>0  ");

        //Tất cả sản phẩm
        if(f.getType()==1){
            if(StringUtils.isNotBlank(f.getGender())){
                sqlBuilder.append("and p.gender=:gender  ");
                parameters.put("gender", f.getGender());
            }
            getFilterPrice(sqlBuilder,f);
            getFilterBrand(sqlBuilder,f,parameters);
            getFilterSort(sqlBuilder,f);
        }
        //product theo thương hiệu
        if(f.getType()==2 && StringUtils.isNotBlank(f.getIdBrand())){
            sqlBuilder.append("and b.id=:idBrand  ");
            parameters.put("idBrand", f.getIdBrand());
            if(StringUtils.isNotBlank(f.getGender())){
                sqlBuilder.append("and p.gender=:gender  ");
                parameters.put("gender", f.getGender());
            }
            getFilterPrice(sqlBuilder,f);
            getFilterSort(sqlBuilder,f);
        }
        //product theo giới tính
        if(f.getType()==3 && StringUtils.isNotBlank(f.getSex())) {
            sqlBuilder.append("and p.gender=:gender  ");
            parameters.put("gender", f.getSex());
            getFilterPrice(sqlBuilder, f);
            getFilterBrand(sqlBuilder, f, parameters);
            getFilterSort(sqlBuilder, f);
        }

        //Tìm kiếm sản phẩm
        if(f.getType()==4 && StringUtils.isNotBlank(f.getSearch())){
            sqlBuilder.append("and lower(p.name) like lower(concat('%',:query,'%'))  ");
            parameters.put("query", perfumeUtils.convertToEnglish(f.getSearch().trim()));
            if(StringUtils.isNotBlank(f.getGender())){
                sqlBuilder.append("and p.gender=:gender  ");
                parameters.put("gender", f.getGender());
            }
            getFilterPrice(sqlBuilder,f);
            getFilterBrand(sqlBuilder,f,parameters);
            getFilterSort(sqlBuilder,f);
        }
        List<Product> lstProduct = namedParameterJdbcTemplate.query(sqlBuilder.toString(), parameters, BeanPropertyRowMapper.newInstance(Product.class));
        return lstProduct;
    }

    @Override
    public List<Product> getListProduct(String search) {
        String query=perfumeUtils.convertToEnglish(search.trim());
        if(!search.equals("all"))
            return productRepository.getProductBySearch(query);
        if(search.equals("all")){
            return productRepository.findAll();
        }
        return null;
    }

    @Override
    @Transactional
    public Product saveOrUpdate(ProductDTO productDTO) throws IOException {
        if(productDTO.getId()==null) {
            productDTO.setCreatedDate(perfumeUtils.getDateNow());
        }
        if(productDTO.getId()!=null){
            productDTO.setCreatedDate(getProductById(productDTO.getId()).getCreatedDate());
            productDTO.setUpdatedDate(perfumeUtils.getDateNow());
        }
        Product product = modelMapper.map(productDTO,Product.class);
        product.setImage(productDTO.getImage());
        Brand brand = brandService.getBrandById(productDTO.getIdBrand());
        product.setBrand(brand);

        //Lưu file
        if(!productDTO.getFileImage().isEmpty()) {
            product.setImage(productDTO.getFileImage().getOriginalFilename());
            MultipartFile fileImage = productDTO.getFileImage();
            //byte[] bytes = fileImage.getBytes();
            //Path path = Paths.get(System.getProperty("user.dir") + "/product/" + fileImage.getOriginalFilename());
            //Files.write(path, bytes);
            fileImage.transferTo(new File(System.getProperty("user.dir") + "/uploads/product/" + fileImage.getOriginalFilename()));
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getListProductByFilterAdmin(FilterProductAdminDTO f) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select p.id id, p.name name, p.image image, p.amount, b.name brandName, p.price, p.status, p.created_date createdDate  ");
        sqlBuilder.append("from product p inner join brand b on p.id_brand= b.id  ");
        sqlBuilder.append("where 1=1  ");

        //tìm sản phẩm
        if(StringUtils.isNotBlank(f.getSearch())){
            sqlBuilder.append("and lower(p.name) like lower(concat('%',:query,'%'))  ");
            parameters.put("query", perfumeUtils.convertToEnglish(f.getSearch().trim()));
        }
        if(StringUtils.isNotBlank(f.getBrand())){
            sqlBuilder.append("and b.id=:id  ");
            parameters.put("id", f.getBrand());
        }
        if(StringUtils.isNotBlank(f.getGender())){
            sqlBuilder.append("and p.gender=:gender  ");
            parameters.put("gender", f.getGender());
        }
        if(StringUtils.isNotBlank(f.getStatus())){
            if("true".equalsIgnoreCase(f.getStatus().trim())){
                sqlBuilder.append("and p.status=true  ");
            }
            if("false".equalsIgnoreCase(f.getStatus().trim())){
                sqlBuilder.append("and p.status=false  ");
            }
        }
        sqlBuilder.append(f.getSort());
        List<ProductDTO> lstProduct = namedParameterJdbcTemplate.query(sqlBuilder.toString(), parameters, BeanPropertyRowMapper.newInstance(ProductDTO.class));
        return lstProduct;
    }


}
