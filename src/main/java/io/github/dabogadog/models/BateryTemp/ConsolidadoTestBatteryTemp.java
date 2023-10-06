package io.github.dabogadog.models.BateryTemp;

import lombok.Builder;

@Builder
public class ConsolidadoTestBatteryTemp {
    public Double temperatureMax;
    public Double temperatureMin;
    public Double temperatureAvg;
    public Double batteryMax;
    public Double batteryMin;
    public Double batteryAvg;
}
