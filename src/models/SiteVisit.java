package models;

import java.util.Date;

public class SiteVisit {
    /**
     * Mandatory fields
     */
    private String page_id; // unique key
    private String verb;
    private Date event_time;
    private String customer_id;

    /**
     * Optional fields
     */
    private Tabs tags; //

    /**
     *
     * we also provide constructor with all fields
     * @param page_id
     * @param verb
     * @param event_time
     * @param customer_id
     * @param tags
     */
    public SiteVisit(String page_id, String verb, Date event_time, String customer_id, Tabs tags) {
        this.page_id = page_id;
        this.verb = verb;
        this.event_time = event_time;
        this.customer_id = customer_id;
        this.tags = tags;
    }

    public SiteVisit()
    {

    }

    public Tabs getTags() {
        return tags;
    }

    public String getPage_id() {
        return page_id;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public String getCustomer_id() {
        return customer_id;
    }


    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setTags(Tabs tags) {

        this.tags = tags;
    }
}