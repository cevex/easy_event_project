package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @NotNull
    private String username;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Participant() {
    }

    public Participant(Long id, String username) {
        super(id);
        this.username = username;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
