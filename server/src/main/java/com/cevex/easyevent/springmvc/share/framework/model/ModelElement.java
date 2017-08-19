package com.cevex.easyevent.springmvc.share.framework.model;

/**
 * Created by cedric on 12/08/17.
 */
public class ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ModelElement() {
    }

    public ModelElement(Long id) {
        this.id = id;
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
}
