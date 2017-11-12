package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Contribution extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private String amount;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Contribution() {

    }

    public Contribution(Long id, String amount) {
        super(id);
        this.amount = amount;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
