package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Id
    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "amount")
    private Integer amount;

//    @ManyToOne
//    @JoinColumn(name = "id_product")
//    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "id_account")
//    private Account account;
}
