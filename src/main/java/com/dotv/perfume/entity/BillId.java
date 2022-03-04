package com.dotv.perfume.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BillId implements Serializable {

    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "id_bill")
    private Integer idBill;

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillId)) return false;
        BillId billId = (BillId) o;
        return getIdProduct().equals(billId.getIdProduct()) && getIdBill().equals(billId.getIdBill());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getIdBill());
    }
}
