package com.cevex.easyevent.springmvc.model;

public class User {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private Double money;
    private Byte[] image;

    public User(Long id, String name, String phone, String email, Double money, Byte[] image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.money = money;
        this.image = image;
    }

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