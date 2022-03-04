package com.dotv.perfume.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

    @EmbeddedId
    private CartId id;

    @Column(name = "amount")
    private Integer amount;

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getId(), cart.getId()) && Objects.equals(getAmount(), cart.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }
}
