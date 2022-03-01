package com.dotv.perfume.dto;

public class CartDTO {
    private Integer idProduct;
    private Integer idUser;
    private Integer amount;

    public Integer getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
