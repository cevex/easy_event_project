package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 50)
    private String phone;

    @Size(min = 3, max = 50)
    private String email;

    private Double money;

    private Byte[] image;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public User() {
    }

    public User(Long id, String name, String phone, String email, Double money, Byte[] image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.money = money;
        this.image = image;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
