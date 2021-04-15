package framework.googleCloudPriceCalculatorApp.service;

import framework.googleCloudPriceCalculatorApp.model.EngineEntity;

import static framework.constants.Constants.GoogleCloudComputeEngineTestDataNames.*;

public class EngineEntityCreator {
    public static EngineEntity createEngineEntity() {

        return EngineEntity.builder()
                .numberOfInstances(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES))
                .OSSoftware(TestDataReader.getTestData(TESTDATA_OS_SOFTWARE))
                .VMClass(TestDataReader.getTestData(TESTDATA_VM_CLASS))
                .VMSeries(TestDataReader.getTestData(TESTDATA_VM_SERIES))
                .instanceType(TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE))
                .numberOfGPU(TestDataReader.getTestData(TESTDATA_NUMBER_OF_GPU))
                .GPUType(TestDataReader.getTestData(TESTDATA_GPU_TYPE))
                .localSSD(TestDataReader.getTestData(TESTDATA_LOCAL_SSD))
                .datacenterLocation(TestDataReader.getTestData(TESTDATA_DATACENTER_LOCATION))
                .committedUsage(TestDataReader.getTestData(TESTDATA_COMMITTED_USAGE))
                .build();
    }
}
