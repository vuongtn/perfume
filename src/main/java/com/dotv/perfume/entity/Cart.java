package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("idProduct")
//    @JoinColumn(name = "id_product")
//    private Product product;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("idAccount")
//    @JoinColumn(name = "id_account")
//    private Account account;
}
