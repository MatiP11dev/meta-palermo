#include <SoapySDR/Device.h>
#include <SoapySDR/Formats.h>
#include <SoapySDR/Logger.hpp>
#include <SoapySDR/Types.hpp>
#include <stdio.h> //printf
#include <stdlib.h> //free
#include <stdint.h>
#include <SoapySDR/Time.hpp>


int main(){
     long long timeNs=10; //timestamp for receive buffer
     const size_t sample_rate=10000;
     uint32_t temp_timestamp = SoapySDR::timeNsToTicks(timeNs, sample_rate);
     timeNs=10002;
     temp_timestamp = SoapySDR::timeNsToTicks(timeNs, sample_rate);
     return 0;
}

