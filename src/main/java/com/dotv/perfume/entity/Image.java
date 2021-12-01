package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "id_product")
//    private Integer idProduct;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="id_product")
    private Image image;
}
