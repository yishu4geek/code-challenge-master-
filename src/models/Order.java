package models;

import java.util.Date;

public class Order {

    /**
     * Mandatory fields
     */
    private String order_id;
    private Date event_time;
    private String verb;
    private String customer_id;
    private String total_amount;

    /**
     * Constructor takes all mandatory fields
     *
     * @param order_id
     * @param event_time
     * @param verb
     * @param customer_id
     * @param total_amount
     */
    public Order(String order_id, Date event_time, String verb, String customer_id, String total_amount) {
        this.order_id = order_id;
        this.event_time = event_time;
        this.verb = verb;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
    }

    public Order() {

    }

    /**
     * Convert string "12.34 USD" into 12.34
     *
     * @return
     */
    public Double getAmountInDigit() {
        double amountInDigit = 0;
        if (total_amount != null && !total_amount.isEmpty()) {
            String[] amountStr = total_amount.trim().split(" ");
            amountInDigit = Double.valueOf(amountStr[0]);
        }

        return amountInDigit;
    }

    /**
     * Method to get currency from "12.34 USD", default is USD
     * @return
     */
    public String getCurrency()
    {
        String currency = "USD";
        if (total_amount != null && !total_amount.isEmpty()) {
            String[] amountStr = total_amount.trim().split(" ");
            currency = amountStr[1];
        }
        return currency;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }


}