package com.dotv.perfume.dto;

import com.dotv.perfume.entity.BillDetail;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private List<BillDetail> billDetails;

    public BillDTO() {
    }

    public BillDTO(Integer id, Integer idAccount, String receiverName, String receiverAddress, String receiverEmail, String receiverPhone, String note, Integer status, Date createDate, Date modifyDate, List<BillDetail> billDetails) {
        this.id = id;
        this.idAccount = idAccount;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverEmail = receiverEmail;
        this.receiverPhone = receiverPhone;
        this.note = note;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.billDetails = billDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }
}
