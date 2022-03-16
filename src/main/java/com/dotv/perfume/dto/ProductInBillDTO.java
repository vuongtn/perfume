package com.dotv.perfume.dto;


import java.math.BigDecimal;

public class ProductInBillDTO {
    private Integer id;
    private String name;
    private Integer amount;
    private BigDecimal currentlyPrice;
    private String image;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentlyPrice() {
        return currentlyPrice;
    }

    public void setCurrentlyPrice(BigDecimal currentlyPrice) {
        this.currentlyPrice = currentlyPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductInBillDTO() {
    }

    public ProductInBillDTO(Integer id, String name, Integer amount, BigDecimal currentlyPrice, String image) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.currentlyPrice = currentlyPrice;
        this.image = image;
    }
}
