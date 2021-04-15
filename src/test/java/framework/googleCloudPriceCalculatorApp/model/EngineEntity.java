package framework.googleCloudPriceCalculatorApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EngineEntity {
    private String numberOfInstances;
    private String OSSoftware;
    private String VMClass;
    private String VMSeries;
    private String instanceType;
    private String numberOfGPU;
    private String GPUType;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;
}
