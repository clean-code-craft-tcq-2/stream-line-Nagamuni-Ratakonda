#include "BMS_DataSender.h"

bool GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate, string inputFile)
{
  bool isDataReadFromSender = false;
  float currentTemperature, currentSOC, currentChargeRate;
  int Readingindex = 0;
  
  FILE *BMSSensorDataFile_fp = fopen("inputFile","r");
  
  if(NULL != BMSSensorDataFile_fp)
  {
    printf("File opened successfully\n");
    for(Readingindex = 0; fscanf(BMSSensorDataFile_fp, "%f %f %f\n", &currentTemperature, &currentSOC, &currentChargeRate)!=EOF; Readingindex++)
    {
      Temperature[Readingindex] = currentTemperature;
      SOC[Readingindex] = currentSOC;
      ChargeRate[Readingindex] = currentChargeRate;
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

void TransferBMSSensorToConsole(float *Temperature, float *SOC, float *ChargeRate)
{
  printf("BMS sensor data from Sender\n");
  printf("Temperature SOC ChargeRate\n");
  for(int i = 0; i < TotalNoOfReadings; i++)
  {
    printf("%f\t%f\t%f\n",Temperature[i],SOC[i],ChargeRate[i]);
  }
}

bool BMSSender(float *Temperature, float *SOC, float *ChargeRate, string inputFile)
{
  bool result = false;
  result = GetBMSDataFromSender(Temperature,SOC,ChargeRate,inputFile);
  TransferBMSSensorToConsole(Temperature,SOC,ChargeRate);
  
  return result;
}
