package com.dotv.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_email")
    private String receiverEmail;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

}
