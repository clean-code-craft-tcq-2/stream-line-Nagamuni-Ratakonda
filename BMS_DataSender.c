#include "BMS_DataSender.h"

bool GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate)
{
  bool isDataReadFromSender = false;
  float currentTemperature, currentSOC, currentChargeRate;
  int Readingindex = 0;
  
  FILE *BMSSensorDataFile_fp = fopen("./BMSSensorData.txt","r");
  
  if(NULL != BMSSensorDataFile_fp)
  {
    while(EOF != fscanf(BMSSensorDataFile_fp, "%f, %f, %f\n", &currentTemperature, &currentSOC, &currentChargeRate))
    {
      Temperature[Readingindex] = currentTemperature;
      SOC[Readingindex] = currentSOC;
      ChargeRate[Readingindex] = currentChargeRate;
      Readingindex++;
    }
    isDataReadFromSender = true;
  }
  else
  {
    printf("Error in opening the File BMSSensorData.txt");
  }
  
  fclose(BMSSensorDataFile_fp);
  
  return isDataReadFromSender;
}
