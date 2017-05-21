package com.cevex.easyevent.springmvc.model;

public class ErrorMessage {

    private String name;
    private String text;

    public ErrorMessage(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

}