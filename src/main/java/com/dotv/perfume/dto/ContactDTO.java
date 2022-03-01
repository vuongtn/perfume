package com.dotv.perfume.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ContactDTO {
    private Integer id;
    @NotBlank(message = "Họ và tên không được để trống.")
    private String fullName;
//    @NotBlank(message = "Email không được để trống.")
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",message = "Email không hợp lệ.")
    private String email;
    @NotBlank(message = "Nội dung phản hồi không được để trống.")
    private String content;
    private java.sql.Timestamp createdDate;
    private Boolean status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
