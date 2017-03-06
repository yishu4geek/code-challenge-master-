package models;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.JsonUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class has methods to do TopXSimpleLTVCustomers, and helper methods
 */
public class Calculator {


    /**
     * method to parse the input JSON file to objects
     *
     * @param jsonPath
     * @return
     * @throws IOException
     */
    public DataSet ingest(String jsonPath) throws IOException {


        //Collections to store data models
        DataSet dataSet = new DataSet();

        List<Object> obj = (List<Object>) new JsonUtils()
                .readJsonFile(jsonPath, Object.class);


        for (Object object : obj) {
            ObjectMapper mapper = new ObjectMapper();
            //ignore case for now during deserialization
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, false);

            /**
             * Ignore unknown properties during deserialization, in case there is any field is unknown,
             * the program will not throw exception and stop
             */
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            byte[] json = mapper.writeValueAsBytes(object);

            if (object != null) {
                //object is a linkedHashMap<String,String> type
                String type = ((LinkedHashMap<String, String>) object).get("type");
                if (type.equals("CUSTOMER")) {
                    Customer customer = mapper.readValue(json, Customer.class);
                    dataSet.getCustomers().add(customer);
                } else if (type.equals("SITE_VISIT")) {
                    SiteVisit siteVisit = mapper.readValue(json, SiteVisit.class);
                    dataSet.getSiteVisits().add(siteVisit);
                } else if (type.equals("IMAGE")) {
                    Image image = mapper.readValue(json, Image.class);
                    dataSet.getImages().add(image);
                } else if (type.equals("ORDER")) {
                    Order order = mapper.readValue(json, Order.class);
                    dataSet.addOrder(order);
                }
            }

        }

        return dataSet;
    }

    /**
     * method calculate top k LTV customers
     *
     * @param dataSet
     * @param numOfTop
     * @throws IOException
     */
    public void topXSimpleLTVCustomers(DataSet dataSet, int numOfTop) throws IOException {
        ArrayList<Output> outputList = new ArrayList<>();

        for (Map.Entry<String, Double> entry : dataSet.getCustomerTotalSpendInDateSetMap().entrySet()) {
            double ltv = getLTV(entry.getValue(), dataSet.getDataSetDurationInWeek());
            Output output = new Output(entry.getKey(), ltv);
            outputList.add(output);

        }
        //sort outputList by ltv
        Collections.sort(outputList, new Output());
        if (numOfTop > outputList.size()) {
            System.out.println("Oops.. there is only " + outputList.size() + " entries inside DataSet, can not get top " + numOfTop);
        } else {
            //write top numOfTop entry into a output.txt
            String outputFolder = "././output";
            String outputFileName = "output.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFolder + "/" + outputFileName));

            for (int i = 0; i < numOfTop; i++) {
                String content = "User: " + outputList.get(i).getCustomer_id() + " LTV: " + outputList.get(i).getLtv() + "\n";
                bw.write(content);
            }
            bw.close();
        }

    }


    /**
     * calculate LTV for each customer
     *
     * @param sumRevenue
     * @param numberOfWeek
     * @return
     */
    public int getLTV(Double sumRevenue, long numberOfWeek) {
        double avgCustomerValuePerWeek = sumRevenue / numberOfWeek;
        return (int) (52 * (avgCustomerValuePerWeek) * 10);
    }
}
