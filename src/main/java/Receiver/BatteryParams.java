package Receiver;

public class BatteryParams {

  public float temp;
  public float soc;
  public float cr;

  public BatteryParams(final String[] values) {
    this.temp = Float.parseFloat(values[0]);
    this.soc = Float.parseFloat(values[1]);
    this.cr = Float.parseFloat(values[2]);
  }

}
