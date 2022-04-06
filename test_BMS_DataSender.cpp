#define CATCH_CONFIG_MAIN 

#include "test/catch.hpp"
#include "BMS_DataSender.h"

TEST_CASE("Testcase to check whether Sensor data is read successfully from a file") {
  float Temperature[TotalNoOfReadings], SOC[TotalNoOfReadings], ChargeRate[TotalNoOfReadings];
  float test_Temperature[TotalNoOfReadings], test_SOC[TotalNoOfReadings], test_ChargeRate[TotalNoOfReadings];
  float temp,soc,chrRate;
  int index;
  
  // Verify data is fetched from sender
  REQUIRE(GetBMSDataFromSender(&Temperature[0],&SOC[0],&ChargeRate[0]) == true);
  
  FILE *test_SensorData_fp = fopen("./BMSSensorData.txt","r");
  
  for(index = 0; fscanf(BMSSensorDataFile_fp, "%f %f %f\n", &temp, &soc, &chrRate)!=EOF; index++)
  {
      test_Temperature[index] = temp;
      test_SOC[index] = soc;
      test_ChargeRate[index] = chrRate;
  }
  
  //Verify that readings are read from input file
  for(index = 0; index < TotalNoOfReadings; index++)
  {
    REQUIRE(Temperature[i] == test_Temperature[i]);
    REQUIRE(SOC[i] == test_SOC[i]);
    REQUIRE(ChargeRate[i] == test_ChargeRate[i]);
  }
  
  fclose(test_SensorData_fp);
}
