package com.dotv.perfume.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartId implements Serializable{
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "id_user")
    private Integer idUser;

    public CartId() {
    }

    public CartId(Integer idProduct, Integer idUser) {
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartId)) return false;
        CartId cartId = (CartId) o;
        return getIdProduct().equals(cartId.getIdProduct()) && getIdUser().equals(cartId.getIdUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getIdUser());
    }


}
