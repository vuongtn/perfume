package com.dotv.perfume.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer id;
    private Integer idTrademark;
    private String name;
    private String origin;
    private String gender;
    private String fragrant;
    private String guarantee;
    private BigDecimal price;
    private String image;
    private String shortDescription;
    private String detailDescription;
    private Integer amount;
    private java.sql.Timestamp createdDate;
    private java.sql.Timestamp updatedDate;
    private String createdBy;
    private String updatedBy;
    private Byte status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTrademark() {
        return this.idTrademark;
    }

    public void setIdTrademark(Integer idTrademark) {
        this.idTrademark = idTrademark;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFragrant() {
        return this.fragrant;
    }

    public void setFragrant(String fragrant) {
        this.fragrant = fragrant;
    }

    public String getGuarantee() {
        return this.guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailDescription() {
        return this.detailDescription;
    }

    public void setDetailDescription(String detailDescription) {this.detailDescription = detailDescription;}

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public java.sql.Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public java.sql.Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(java.sql.Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
