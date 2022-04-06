#include "BMS_DataSender.h"

bool GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate)
{
  bool isDataReadFromSender = false;
  float currentTemperature, currentSOC, currentChargeRate;
  int Readingindex = 0;
  
  FILE *BMSSensorDataFile_fp = fopen("./BMSSensorData.txt","r");
  
  if(NULL != BMSSensorDataFile_fp)
  {
    printf("File opened successfully");
    for(Readingindex = 0; fscanf(BMSSensorDataFile_fp, "%f, %f, %f\n", &currentTemperature, &currentSOC, &currentChargeRate)!=EOF; Readingindex++)
    {
      Temperature[Readingindex] = currentTemperature;
      SOC[Readingindex] = currentSOC;
      ChargeRate[Readingindex] = currentChargeRate;
      printf("Loop entered successfully %d",Readingindex);
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
