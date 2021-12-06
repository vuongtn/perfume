package com.dotv.perfume.entity;

import com.dotv.perfume.dto.ProductInCartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@NamedNativeQuery(name = "ProductTest",
        query = "select p.id, p.name, p.price, c.amount, i.name as nameImg from cart c " +
                "join product p on c.id_product = p.id " +
                "join image i on p.id=i.id_product " +
                "and id_account=?1 and i.type='main'",
        resultSetMapping = "CartMapping")
@SqlResultSetMapping(name = "CartMapping",
        classes = @ConstructorResult(targetClass = ProductInCartDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "amount"),
                        @ColumnResult(name = "nameImg")
                }))
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "cart")
public class Cart  {
    @EmbeddedId
    private CartId id;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAccount")
    @JoinColumn(name = "id_account")
    private Account account;
}
