#include <stdio.h>
#include <stdbool.h>

#define TotalNoOfReadings 10


bool GetBMSDataFromSender(float *Temperature, float *SOC, float *ChargeRate, char inputFile);
void TransferBMSSensorToConsole(float *Temperature, float *SOC, float *ChargeRate);
bool BMSSender(float *Temperature, float *SOC, float *ChargeRate, string inputFile);
