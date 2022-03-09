package com.dotv.perfume.dto;

import java.math.BigDecimal;

public class ProductInCartDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer amount;
    private String image;

    public ProductInCartDTO() {
    }

    public ProductInCartDTO(Integer id, String name, BigDecimal price, Integer amount, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
