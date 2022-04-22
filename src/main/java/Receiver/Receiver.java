package Receiver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Receiver {

  public static final String CAPTION = "Temperature SOC ChargeRate";
  public static final String TEMP = "TEMPERATURE";
  public static final String SOC = "STATE OF CHARGE";
  public static final String CR = "CHARGE RATE";

  public static float minTemp;
  public static float maxTemp;
  public static float movAvgTemp;

  public static float minSoc;
  public static float maxSoc;
  public static float movAvgSoc;

  public static float minCr;
  public static float maxCr;
  public static float movAvgCr;

  public static void main(final String args[]) throws Exception {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String line = scanner.nextLine();
      if (line.equalsIgnoreCase(CAPTION)) {
        break;
      }
    }
    processConsoleInput(scanner);

    printToConsole(TEMP, minTemp, maxTemp, movAvgTemp);
    printToConsole(SOC, minSoc, maxSoc, movAvgSoc);
    printToConsole(CR, minCr, maxCr, movAvgCr);
    scanner.close();
  }

  public static void processConsoleInput(final Scanner scanner) throws Exception {
    List<Float> tempList = new ArrayList<>();
    List<Float> socList = new ArrayList<>();
    List<Float> crList = new ArrayList<>();
    for (int i = 1; i <= 10; ++i) {
      String line = scanner.nextLine();
      String[] values = getValuesfromConsoleLine(line);
      updateBatteryParamValues(values, tempList, socList, crList);
    }
    calculateStatisticsforBatteryParams(tempList, socList, crList);
  }
  
  public static String[] getValuesfromConsoleLine(final String line) {
    String trimmedLine = line.replaceAll("\\s+", " ");
    return trimmedLine.split(" ", 3);
  }

  public static void updateBatteryParamValues(final String[] values, final List<Float> tempList,
      final List<Float> socList, final List<Float> crList) {
    tempList.add(Float.parseFloat(values[0]));
    socList.add(Float.parseFloat(values[1]));
    crList.add(Float.parseFloat(values[2]));
  }

  public static void calculateStatisticsforBatteryParams(final List<Float> tempList, final List<Float> socList,
      final List<Float> crList) {

    // Minimum Value
    minTemp = getMinValue(tempList);
    minSoc = getMinValue(socList);
    minCr = getMinValue(crList);

    // Maximum Value
    maxTemp = getMaxValue(tempList);
    maxSoc = getMaxValue(socList);
    maxCr = getMaxValue(crList);

    // Moving Average
    movAvgTemp = getMovingAvg(tempList);
    movAvgSoc = getMovingAvg(socList);
    movAvgCr = getMovingAvg(crList);

  }

  public static float getMinValue(final List<Float> list) {
    return Collections.min(list);
  }

  public static float getMaxValue(final List<Float> list) {
    return Collections.max(list);
  }

  public static float getMovingAvg(final List<Float> list) {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    if ((list == null) || list.isEmpty()) {
      return 0.0F;
    }

    List<Float> filteredList = getLastFiveValues(list);
    Float sum = filteredList.parallelStream().reduce(0.0F, Float::sum);
    float average = Float.parseFloat(decimalFormat.format(Math.round((sum / filteredList.size()) * 100.0D)));

    return (average / 100.0F);
  }

  public static List<Float> getLastFiveValues(final List<Float> valuesList) {
    if (valuesList.size() >= 5) {
      return valuesList.subList(valuesList.size() - 5, valuesList.size());
    }
    return valuesList;
  }

  public static void printToConsole(final String batteryParam, final float minValue, final float maxValue,
      final float movAvg) {
    System.out.println(batteryParam);
    System.out.println("Minimum " + batteryParam + ": " + minValue);
    System.out.println("Maximum " + batteryParam + ": " + maxValue);
    System.out.println("Moving Average for " + batteryParam + ": " + movAvg);
    System.out.println(" ");
  }

}


