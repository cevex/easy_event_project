package com.cevex.easyevent.springmvc.share.rest.error.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code", "resource", "field", "type", "message"})
public class FieldValidationErrorCause extends ErrorCause {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Integer code;

    /**
     * REST resource holding the field
     */
    private String resource;

    /**
     * The field with error
     */
    private String field;

    /**
     * The type of error
     */
    private String type;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public FieldValidationErrorCause() {
        super();
    }

    public FieldValidationErrorCause(String message, Integer code, String resource, String field, String type) {
        super(type, message);
        this.code = code;
        this.resource = resource;
        this.field = field;
        this.type = type;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

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