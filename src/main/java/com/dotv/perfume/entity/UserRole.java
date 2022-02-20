package com.dotv.perfume.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @Column(name = "id_user")
    private Integer idUser;

    @Id
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "view_role")
    private Byte viewRole;

    @Column(name = "insert_role")
    private Byte insertRole;

    @Column(name = "update_role")
    private Byte updateRole;

    @Column(name = "delete_role")
    private Byte deleteRole;

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

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRole() {
        return this.idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Byte getViewRole() {
        return this.viewRole;
    }

    public void setViewRole(Byte viewRole) {
        this.viewRole = viewRole;
    }

    public Byte getInsertRole() {
        return this.insertRole;
    }

    public void setInsertRole(Byte insertRole) {
        this.insertRole = insertRole;
    }

    public Byte getUpdateRole() {
        return this.updateRole;
    }

    public void setUpdateRole(Byte updateRole) {
        this.updateRole = updateRole;
    }

    public Byte getDeleteRole() {
        return this.deleteRole;
    }

    public void setDeleteRole(Byte deleteRole) {
        this.deleteRole = deleteRole;
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
