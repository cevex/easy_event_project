package com.cevex.easyevent.springmvc.share.rest.error.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "resource", "field", "type", "message" })
public class ValidationErrorCause extends ErrorCause {

    private Integer code;
    private String resource;
    private String field;
    private String type;


    public ValidationErrorCause() {
        super();
    }

    public ValidationErrorCause(String message, Integer code, String resource, String field, String type) {
        super(message);
        this.code = code;
        this.resource = resource;
        this.field = field;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}