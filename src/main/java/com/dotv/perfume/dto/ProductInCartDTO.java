package com.dotv.perfume.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private int amount;
    private String nameImg;
}
