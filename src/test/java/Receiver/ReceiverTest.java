package Receiver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReceiverTest {
  @Test
  public void testGetLastFiveValues() {
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    List<Float> outList = Receiver.getLastFiveValues(list);
    Assert.assertEquals(Arrays.asList(0.6f, 0.7f, 0.8f, 0.9f, 1.0f), outList);
  }

  @Test
  public void testGetMovingAvg() {
    List<Float> list = Arrays.asList(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f);
    float movAvg = Receiver.getMovingAvg(list);
    Assert.assertEquals(0.8f, movAvg, 0.0f);
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
}
