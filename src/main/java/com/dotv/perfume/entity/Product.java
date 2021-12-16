package com.dotv.perfume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "origin")
    private String origin;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "detail")
    private String detail;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "status")
    private Boolean status;

    public Product(Integer idCategory, String name, BigDecimal price, Integer gender, String origin, String capacity, Integer amount, String shortDescription, String detail, LocalDate createDate, Integer discount, Boolean status) {
        this.idCategory = idCategory;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.origin = origin;
        this.capacity = capacity;
        this.amount = amount;
        this.shortDescription = shortDescription;
        this.detail = detail;
        this.createDate = createDate;
        this.discount = discount;
        this.status = status;
    }

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="id_category")
//    private Category category;
//
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Image> images;
//    @OneToMany
//    @JoinColumn(name="id_product")
//    private List<Image> images;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BillDetail> billDetails;
}
