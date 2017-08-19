package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @NotNull
    private Long eventId;

    @Size(min = 3, max = 50)
    private String label;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime date;

    private List<Contribution> contributionList;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Expense() {
    }

    public Expense(Long id, Long eventId, String label, DateTime date, List<Contribution> contributionList) {
        super(id);
        this.eventId = eventId;
        this.label = label;
        this.date = date;
        this.contributionList = contributionList;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Contribution> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<Contribution> contributionList) {
        this.contributionList = contributionList;
    }
}
