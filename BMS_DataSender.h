#include <stdio.h>
#include <stdbool.h>

#define TotalNoOfReadings 10


void GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate, char *inputFile);
void TransferBMSSensorToConsole(float *Temperature, float *SOC, float *ChargeRate);
void BMSSender(float *Temperature, float *SOC, float *ChargeRate, char *inputFile);
