package com.dotv.perfume.dto;

public class OrderDTO {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String note;
    private String payment;

    public OrderDTO(String fullName, String email, String phone, String address, String note, String payment) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.payment = payment;
    }

    public OrderDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
