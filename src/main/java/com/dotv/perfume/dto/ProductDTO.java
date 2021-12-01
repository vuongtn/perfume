package com.dotv.perfume.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Integer id;
    private Integer idCategory;
    @NotBlank(message = "Không được để trống")
    private String name;
    @NotBlank(message = "Không được để trống")
    private BigDecimal price;
    @NotBlank(message = "Không được để trống")
    private Integer gender;
    @NotBlank(message = "Không được để trống")
    private String origin;
    @NotBlank(message = "Không được để trống")
    private String capacity;
    @NotBlank(message = "Không được để trống")
    private Integer amount;
    @NotBlank(message = "Không được để trống")
    private String shortDescription;
    @NotBlank(message = "Không được để trống")
    private String detail;
}
