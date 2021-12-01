package com.dotv.perfume.dto;

public class CartDTO {
    private Integer idProduct;
    private Integer idAccount;
    private Integer amount;

    public Integer getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
