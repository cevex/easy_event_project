package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private String name;
    private String text;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

}