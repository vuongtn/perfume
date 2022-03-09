package com.dotv.perfume.entity;

import com.dotv.perfume.dto.ProductInCartDTO;

import javax.persistence.*;
import java.util.Objects;

@NamedNativeQuery(name = "ProductTest",
        query = "select p.id, p.name, p.price, c.amount, p.image from cart c " +
                "join product p on c.id_product = p.id " +
                "and id_user=?1",
        resultSetMapping = "CartMapping")
@SqlResultSetMapping(name = "CartMapping",
        classes = @ConstructorResult(targetClass = ProductInCartDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "amount"),
                        @ColumnResult(name = "image")
                }))
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

    public Cart() {
    }

    public Cart(CartId id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }
}
