package io.github.dabogadog.models.CpuMem;

import lombok.Builder;

@Builder
public class ConsolidadoTestCpuMem {
    public Double sysMax;
    public Double sysMin;
    public Double sysAvg;
    public Double appMax;
    public Double appMin;
    public Double appAvg;
}
