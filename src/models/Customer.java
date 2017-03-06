package models;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

public class Customer {
    // mandatory fields
    private String customer_id;
    private Date event_time;
    private String verb;
    // optional fields
    private String last_name;
    private String adr_city;
    private String adr_state;



    public Customer()
    {

    }

    public Customer(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * Provide setters for UPDATE customer
     */


    @JsonSetter("key")
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getCustomer_id() {
        return customer_id;
    }


    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getAdr_state() {
        return adr_state;
    }

    public void setAdr_state(String adr_state) {
        this.adr_state = adr_state;
    }


}