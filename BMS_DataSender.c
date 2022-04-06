#include "BMS_DataSender.h"

void GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate, char *inputFile)
{
  float currentTemperature, currentSOC, currentChargeRate;
  int Readingindex = 0;
  
  FILE *BMSSensorDataFile_fp = fopen(inputFile,"r");
  
  for(Readingindex = 0; fscanf(BMSSensorDataFile_fp, "%f %f %f\n", &currentTemperature, &currentSOC, &currentChargeRate)!=EOF; Readingindex++)
  {
      Temperature[Readingindex] = currentTemperature;
      SOC[Readingindex] = currentSOC;
      ChargeRate[Readingindex] = currentChargeRate;
  }

  fclose(BMSSensorDataFile_fp);
  
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

void BMSSender(float *Temperature, float *SOC, float *ChargeRate, char *inputFile)
{
  GetBMSDataFromSender(Temperature,SOC,ChargeRate,inputFile);
  TransferBMSSensorToConsole(Temperature,SOC,ChargeRate);
}
