package models;

import java.util.Date;

public class Image {
    /**
     * Mandatory fields
     */
    private String image_id;
    private String verb;
    private Date event_time;
    private String customer_id;
    /**
     * Optional fields
     */
    private String camera_make;
    private String camera_model;

    /**
     * Constructor takes mandatory fields
     *
     * @param image_id
     * @param verb
     * @param event_time
     * @param customer_id
     */
    public Image(String image_id, String verb, Date event_time, String customer_id) {
        this.image_id = image_id;
        this.verb = verb;
        this.event_time = event_time;
        this.customer_id = customer_id;
    }

    public Image() {

    }

    /**
     * Constructor takes all fiedls
     *
     * @param image_id
     * @param verb
     * @param event_time
     * @param customer_id
     * @param camera_make
     * @param camera_model
     */
    public Image(String image_id, String verb, Date event_time, String customer_id, String camera_make, String camera_model) {
        this.image_id = image_id;
        this.verb = verb;
        this.event_time = event_time;
        this.customer_id = customer_id;
        this.camera_make = camera_make;
        this.camera_model = camera_model;
    }


    /**
     * Functions
     */

    /**
     * Function to do the upload photo work
     *
     * @return true if upload successfully
     */
    public boolean upload() {
        return false;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
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

    public String getCamera_make() {
        return camera_make;
    }

    public void setCamera_make(String camera_make) {
        this.camera_make = camera_make;
    }

    public String getCamera_model() {
        return camera_model;
    }

    public void setCamera_model(String camera_model) {
        this.camera_model = camera_model;
    }
}