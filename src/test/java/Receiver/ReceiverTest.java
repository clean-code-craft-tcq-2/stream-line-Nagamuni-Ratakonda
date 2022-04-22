package Receiver;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReceiverTest {
  
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(this.outputStreamCaptor));
  }

  @Test
  public void testPrintToConsole() {
    Receiver.printToConsole(Receiver.TEMP, 1.0f, 15.0f, 9.0f);

    Assert.assertEquals("TEMPERATURE\r\n" + "Minimum TEMPERATURE: 1.0\r\n" + "Maximum TEMPERATURE: 15.0\r\n" +
        "Moving Average for TEMPERATURE: 9.0", this.outputStreamCaptor.toString().trim());
  }
  
  @Test
  public void testGetLastFiveValues() {
    // List with more than 5 values
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    List<Float> outList = Receiver.getLastFiveValues(list);
    Assert.assertEquals(Arrays.asList(0.6f, 0.7f, 0.8f, 0.9f, 1.0f), outList);
    // List with less than 5
    List<Float> list1 = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f);
    List<Float> outList1 = Receiver.getLastFiveValues(list1);
    Assert.assertEquals(Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f), outList1);
  }

  @Test
  public void testGetMovingAvg() {
    // List with values
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    float movAvg = Receiver.getMovingAvg(list);
    Assert.assertEquals(0.8f, movAvg, 0.0f);
    // Empty list
    List<Float> list1 = Collections.<Float> emptyList();
    float movAvgOfEmptyList = Receiver.getMovingAvg(list1);
    Assert.assertEquals(0.0f, movAvgOfEmptyList, 0.0f);
    // null value
    List<Float> list2 = null;
    float movAvgOfNull = Receiver.getMovingAvg(list2);
    Assert.assertEquals(0.0f, movAvgOfNull, 0.0f);
  }

  @Test
  public void testGetMinValue() {
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    float min = Receiver.getMinValue(list);
    Assert.assertEquals(0.1f, min, 0.0f);
  }

  @Test
  public void testGetMaxValue() {
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    float max = Receiver.getMaxValue(list);
    Assert.assertEquals(1.0f, max, 0.0f);
  }

  @Test
  public void testGetValuesfromConsoleLine() {
    String line = "1.000000    2.000000    0.100000";
    String[] values = Receiver.getValuesfromConsoleLine(line);
    Assert.assertEquals("1.000000", values[0]);
    Assert.assertEquals("2.000000", values[1]);
    Assert.assertEquals("0.100000", values[2]);
  }

  @Test
  public void testCalculateStatisticsforBatteryParams() {
    List<Float> tempList = Arrays.asList(1.00f, 3.00f, 5.000f, 7.0f, 9.000f, 11.0f, 13f, 15.00f, 17.0f, 19.0f);
    List<Float> socList = Arrays.asList(2.0f, 4.00000f, 6.00f, 8f, 10.0f, 12.000f, 14.0f, 16f, 18.00f, 20.0f);
    List<Float> crList = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    Receiver.calculateStatisticsforBatteryParams(tempList, socList, crList);
    Assert.assertEquals(0.1f, Receiver.minCr, 0.0f);
    Assert.assertEquals(20.0f, Receiver.maxSoc, 0.0f);
    Assert.assertEquals(15.0f, Receiver.movAvgTemp, 0.0f);
  }

  @Test
  public void testUpdateBatteryParamValues() {
    List<Float> tempList = new ArrayList<>();
    List<Float> socList = new ArrayList<>();
    List<Float> crList = new ArrayList<>();
    String[] value1 = { "7.0000", "8.0000", "0.4000" };
    String[] value2 = { "2.0000", "12.0000", "0.3000" };
    String[] value3 = { "5.0000", "2.0000", "0.5000" };
    Receiver.updateBatteryParamValues(value1, tempList, socList, crList);
    Assert.assertEquals(7.0000f, tempList.get(0), 0.0f);
    Receiver.updateBatteryParamValues(value2, tempList, socList, crList);
    Assert.assertEquals(0.3000f, crList.get(crList.size() - 1), 0.0f);
    Receiver.updateBatteryParamValues(value3, tempList, socList, crList);
    Assert.assertEquals(2.0000f, socList.get(socList.size() - 1), 0.0f);
  }
}
