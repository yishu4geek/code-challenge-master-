package models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class DataSet {
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private ArrayList<Image> images;
    private ArrayList<SiteVisit> siteVisits;

    /**
     * Total spend for each customer during complete dataset time frame
     * key: customer_id,
     * value: customerTotalSpendInDateSet
     */
    private HashMap<String, Double> customerTotalSpendInDateSetMap;
    /**
     * The start event date of the DateSet
     */
    private Date firstEventDateOfDateSet;
    /**
     * The last event date of the DateSet
     */
    private Date lastEvenDateOfDateSet;

    public DataSet() {
        customers = new ArrayList<>();
        orders = new ArrayList<>();
        images = new ArrayList<>();
        siteVisits = new ArrayList<>();
        customerTotalSpendInDateSetMap = new HashMap<>();
        firstEventDateOfDateSet = new Date(Long.MAX_VALUE);
        lastEvenDateOfDateSet = new Date(Long.MIN_VALUE);
    }


    /**
     * This method is to add a new Order into orders arraylist
     * also when adding a new order, we will update the customerTotalSpend hashMap
     * if map contains the customer id, then add the new amount to the existing customer
     * otherwise, put the new customer id and the amount into customerTotalSpend hashMap
     * so after add all order to orders, we will get all the customerTotalspend collected
     *
     * @param order
     */
    public void addOrder(Order order) {

        orders.add(order);
        updateFirstAndLastEventDate(order);
        updateUserSpendAmount(order);
    }

    /**
     * Method to update first and last event Date of Data set
     *
     * @param order
     */
    public void updateFirstAndLastEventDate(Order order) {
        if (order != null) {
            Date earliestDate = order.getEvent_time().before(this.firstEventDateOfDateSet) ? order.getEvent_time() : this.firstEventDateOfDateSet;
            Date lastDate = order.getEvent_time().after(this.lastEvenDateOfDateSet) ? order.getEvent_time() : this.lastEvenDateOfDateSet;
            this.firstEventDateOfDateSet = earliestDate;
            this.lastEvenDateOfDateSet = lastDate;
        }

    }

    /**
     * Method to update user total spent amount while inserting a new order to
     * hashmap
     *
     * @param order
     */
    public void updateUserSpendAmount(Order order) {
        if (order != null) {
            if (customerTotalSpendInDateSetMap.containsKey(order.getCustomer_id())) {
                double amount = customerTotalSpendInDateSetMap.get(order.getCustomer_id());
                customerTotalSpendInDateSetMap.put(order.getCustomer_id(), amount + order.getAmountInDigit());
            } else {
                customerTotalSpendInDateSetMap.put(order.getCustomer_id(), order.getAmountInDigit());
            }
        }

    }


    /**
     * convert date to calendar
     *
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }


    /**
     * get number of weeks between two date
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long getFullWeeks(Calendar d1, Calendar d2) {

        Instant d1i = Instant.ofEpochMilli(d1.getTimeInMillis());
        Instant d2i = Instant.ofEpochMilli(d2.getTimeInMillis());

        LocalDateTime startDate = LocalDateTime.ofInstant(d1i, ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(d2i, ZoneId.systemDefault());

        return ChronoUnit.WEEKS.between(startDate, endDate);
    }

    /**
     * @return
     */
    public long getDataSetDurationInWeek() {
        Calendar first = dateToCalendar(this.firstEventDateOfDateSet);
        Calendar last = dateToCalendar(this.lastEvenDateOfDateSet);
        long weeks = getFullWeeks(first, last);
        return weeks;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<SiteVisit> getSiteVisits() {
        return siteVisits;
    }

    public void setSiteVisits(ArrayList<SiteVisit> siteVisits) {
        this.siteVisits = siteVisits;
    }

    public HashMap<String, Double> getCustomerTotalSpendInDateSetMap() {
        return customerTotalSpendInDateSetMap;
    }


    public void setCustomerTotalSpendInDateSetMap(HashMap<String, Double> customerTotalSpendInDateSetMap) {
        this.customerTotalSpendInDateSetMap = customerTotalSpendInDateSetMap;
    }

    /**
     * @return
     */
    public Date getFirstEventDateOfDateSet() {
        return firstEventDateOfDateSet;
    }

    public void setFirstEventDateOfDateSet(Date firstEventDateOfDateSet) {
        this.firstEventDateOfDateSet = firstEventDateOfDateSet;
    }

    public Date getLastEvenDateOfDateSet() {
        return lastEvenDateOfDateSet;
    }

    public void setLastEvenDateOfDateSet(Date lastEvenDateOfDateSet) {
        this.lastEvenDateOfDateSet = lastEvenDateOfDateSet;
    }
}
