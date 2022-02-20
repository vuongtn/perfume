package com.dotv.perfume.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private java.sql.Timestamp createdDate;

    @Column(name = "updated_date")
    private java.sql.Timestamp updatedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "status")
    private Byte status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public java.sql.Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(java.sql.Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
