package models;

import java.util.Comparator;


public class Output implements Comparator<Output>{
    private String customer_id;
    private Double ltv;

    public Output()
    {

    }
    public Output(String customer_id, Double ltv) {
        this.customer_id = customer_id;
        this.ltv = ltv;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Double getLtv() {
        return ltv;
    }

    public void setLtv(Double ltv) {
        this.ltv = ltv;
    }

    @Override
    /**
     * Output order by ltv
     */
    public int compare(Output o1, Output o2) {
        return (int) (o2.ltv-o1.ltv);
    }
}
