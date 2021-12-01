package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="status")
    private Boolean status;

    /*
    * OneToMany: LAZY
    ManyToOne: EAGER
    ManyToMany: LAZY
    OneToOne: EAGER
    * */
//    @OneToMany(mappedBy = "category",fetch =FetchType.LAZY)
//    private List<Product> products;
//    @OneToMany
//    @JoinColumn(name="id_category")
//    private List<Product> products;
}
