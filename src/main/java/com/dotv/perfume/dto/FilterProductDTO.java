package com.dotv.perfume.dto;

public class FilterProductDTO {
    Integer type;
    String idBrand;
    String sex;
    String search;
    String sortBy;
    String gender;
    Integer prices[];
    Integer brands[];

    public FilterProductDTO() {

    }

    public String getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(String idBrand) {
        this.idBrand = idBrand;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer[] getPrices() {
        return prices;
    }

    public void setPrices(Integer[] prices) {
        this.prices = prices;
    }

    public Integer[] getBrands() {
        return brands;
    }

    public void setBrands(Integer[] brands) {
        this.brands = brands;
    }
}
