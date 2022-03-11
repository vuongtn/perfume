package com.dotv.perfume.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {
    @EmbeddedId
    private UserRoleId id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "created_date")
    private java.sql.Timestamp createdDate;

    @Column(name = "updated_date")
    private java.sql.Timestamp updatedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idRole")
    @JoinColumn(name = "id_role")
    private Role role;

    public UserRole() {
    }

    public UserRole(UserRoleId id, String roleName, Timestamp createdDate, Timestamp updatedDate, String createdBy, String updatedBy, Boolean status, User user, Role role) {
        this.id = id;
        this.roleName = roleName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = status;
        this.user = user;
        this.role = role;
    }
    public UserRole(UserRoleId id, String roleName, Timestamp createdDate, Boolean status, User user, Role role) {
        this.id = id;
        this.roleName = roleName;
        this.createdDate = createdDate;
        this.status = status;
        this.user = user;
        this.role = role;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(getId(), userRole.getId()) && Objects.equals(getRoleName(), userRole.getRoleName()) && Objects.equals(getCreatedDate(), userRole.getCreatedDate()) && Objects.equals(getUpdatedDate(), userRole.getUpdatedDate()) && Objects.equals(getCreatedBy(), userRole.getCreatedBy()) && Objects.equals(getUpdatedBy(), userRole.getUpdatedBy()) && Objects.equals(getStatus(), userRole.getStatus()) && Objects.equals(getUser(), userRole.getUser()) && Objects.equals(getRole(), userRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName(), getCreatedDate(), getUpdatedDate(), getCreatedBy(), getUpdatedBy(), getStatus(), getUser(), getRole());
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
