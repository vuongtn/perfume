package com.dotv.perfume.dto;

public class BillDTO {
    private Integer id;
    private Integer idAccount;
    private String receiverName;
    private String receiverAddress;
    private String receiverEmail;
    private String receiverPhone;
    private String note;
    private Integer status;
    private java.sql.Date createDate;
    private java.sql.Date modifyDate;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverEmail() {
        return this.receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverPhone() {
        return this.receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.sql.Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }

    public java.sql.Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(java.sql.Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
