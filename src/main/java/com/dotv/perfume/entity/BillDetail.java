package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill_detail")
public class BillDetail implements Serializable {
    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Id
    @Column(name = "id_bill")
    private Integer idBill;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "currently_price")
    private BigDecimal currentlyPrice;

//    @ManyToOne
//    @JoinColumn(name = "id_product")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "id_bill")
//    private Bill bill;
}
