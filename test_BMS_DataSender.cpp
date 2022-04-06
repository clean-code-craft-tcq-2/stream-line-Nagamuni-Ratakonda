#define CATCH_CONFIG_MAIN 

#include "test/catch.hpp"

TEST_CASE("Testcase to check whether Sensor data is read successfully from a file") {
  float Temperature[10], SOC[10], ChargeRate[10];
  REQUIRE(GetBMSDataFromSender(&Temperature[0],&SOC[0],&ChargeRate[0]) == true);
}
