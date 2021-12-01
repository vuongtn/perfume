package com.dotv.perfume.dto;

import java.math.BigDecimal;

public class BillDetailDTO {
    private Integer idProduct;
    private Integer idBill;
    private Integer amount;
    private BigDecimal currentlyPrice;

    public Integer getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdBill() {
        return this.idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentlyPrice() {
        return this.currentlyPrice;
    }

    public void setCurrentlyPrice(BigDecimal currentlyPrice) {
        this.currentlyPrice = currentlyPrice;
    }
}
