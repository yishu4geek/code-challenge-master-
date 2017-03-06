package test;

import models.Calculator;
import models.DataSet;
import models.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


public class UnitTests {

    @Test
    /**
     * test Ingest method check if this method ingest event correctly
     */
    public void testIngest() throws IOException, ParseException {
        String jsonPath = "././input/order20.json";
        Calculator calculator = new Calculator();
        DataSet dataSet = calculator.ingest(jsonPath);
        String tdate1 = "2017-02-10T08:16:49";
        String tdate2 = "2017-03-01T08:25:49";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'Hh:mm:ss");
        Date startDate = df.parse(tdate1);
        Date endDate = df.parse(tdate2);
        Assert.assertEquals(startDate,dataSet.getFirstEventDateOfDateSet());
        Assert.assertEquals(endDate,dataSet.getLastEvenDateOfDateSet());
        Assert.assertEquals(Double.valueOf(92.68), dataSet.getCustomerTotalSpendInDateSetMap().get("58bb52dabf1457d15ee79eed"));
        Assert.assertEquals(Double.valueOf(76.05), dataSet.getCustomerTotalSpendInDateSetMap().get("58bb52daa65501441a8b877f"));
        Assert.assertEquals(Double.valueOf(82.41), dataSet.getCustomerTotalSpendInDateSetMap().get("58bb52dae49a678e3cec69da"));

    }
    @Test
    /**
     * method to test topXSimpleLTVCustomers
     */
    public void testTopXSimpleLTVCustomers() throws IOException {
        String jsonPath = "././input/order20.json";
        Calculator calculator = new Calculator();
        DataSet dataSet = calculator.ingest(jsonPath);
        calculator.topXSimpleLTVCustomers(dataSet,3);

        Path p1 = Paths.get("././output/output.txt");
        Path p2 = Paths.get("././output/testTopK.txt");
        byte[] f1 = Files.readAllBytes(p1);
        byte[] f2 = Files.readAllBytes(p2);
        Boolean check = Arrays.equals(f1,f2);
        Assert.assertEquals(true,check);

    }

    @Test
    /**
     * negative test ingest method
     */
    public void testIngestfailed() throws IOException {
        String jsonPath2 = "././input/testFailInput.json";
        Calculator calculator = new Calculator();
        DataSet dataSet2 = calculator.ingest(jsonPath2);
        Assert.assertEquals(Double.valueOf(87.86), dataSet2.getCustomerTotalSpendInDateSetMap().get("58bb52dabf1457d15ee79eed"));
        Assert.assertEquals(null, dataSet2.getCustomerTotalSpendInDateSetMap().get("58bb52daa65501441a8b877f"));
    }

    /**
     * test updateFirstAndLastEventDate method check if firstEventDateOfDateSet and lastEvenDateOfDateSet field are updated correctly
     * @throws ParseException
     */
    @Test
    public void testUpdateFirstAndLastEventDate() throws ParseException {
        String tdate1 = "2017-02-08";
        String tdate2 = "2017-01-08";
        String tdate3 = "2017-01-01";
        String tdate4 = "2017-03-08";
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = df.parse(tdate1);
        Date date2 = df.parse(tdate2);
        Date date3 = df.parse(tdate3);
        Date date4 = df.parse(tdate4);

        Order order1 = new Order("58bb52dada5031b795636",date3,"NEW","58bb52da382a518b4f9370ed","12.34 USD");
        Order order2 = new Order("52323ddada5031b795634",date4,"NEW","58bb52da382a518b4f9371ed","10.34 USD");
        DataSet ds = new DataSet();
        ds.setFirstEventDateOfDateSet(date1);
        ds.setLastEvenDateOfDateSet(date2);
        ds.updateFirstAndLastEventDate(order1);
        ds.updateFirstAndLastEventDate(order2);
        Assert.assertEquals(date3,ds.getFirstEventDateOfDateSet());
        Assert.assertEquals(date4,ds.getLastEvenDateOfDateSet());
    }

    /**
     * test updateUserSpendAmount method if customer sum spend are updated correctly
     * @throws ParseException
     */
    @Test
    public void testUpdateUserSpendAmount() throws ParseException {
        String tdate3 = "2017-01-01";
        String tdate4 = "2017-03-08";
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date3 = df.parse(tdate3);
        Date date4 = df.parse(tdate4);
        Order order1 = new Order("58bb52dada5031b795636",date3,"NEW","58bb52da382a518b4f9370ed","12.34 USD");
        Order order2 = new Order("52323ddada5031b795634",date4,"NEW","58bb52da382a518b4f9371ed","10.34 USD");

        DataSet dataSet = new DataSet();
        HashMap<String,Double> map = new HashMap<>();
        map.put("58bb52da382a518b4f9370ed",3.2);
        dataSet.setCustomerTotalSpendInDateSetMap(map);
        dataSet.updateUserSpendAmount(order1);
        dataSet.updateUserSpendAmount(order2);

        Assert.assertEquals(Double.valueOf(15.54), dataSet.getCustomerTotalSpendInDateSetMap().get("58bb52da382a518b4f9370ed"));
        Assert.assertEquals(Double.valueOf(10.34), dataSet.getCustomerTotalSpendInDateSetMap().get("58bb52da382a518b4f9371ed"));
    }
}
