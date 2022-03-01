package com.dotv.perfume.dto;

public class UserRoleDTO {
    private Integer idUser;
    private Integer idRole;
    private String roleName;
    private Byte viewRole;
    private Byte insertRole;
    private Byte updateRole;
    private Byte deleteRole;
    private java.sql.Timestamp createdDate;
    private java.sql.Timestamp updatedDate;
    private String createdBy;
    private String updatedBy;
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
