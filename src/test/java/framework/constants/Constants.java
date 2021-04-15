package framework.constants;

public class Constants {
    public static class TimeVariables {
        public static final int WAIT_TIMEOUT_10_SECONDS = 10;
    }

    public static class GoogleCloudComputeEngineURLs {
        public static final String GOOGLE_CLOUD_HOME_URL = "https://cloud.google.com/";
        public static final String TEMP_MAIL_HOME_URL = "https://mail.tm/en/";
    }

    public static class GoogleCloudServices {
        public static final String GOOGLE_CLOUD_PRICE_CALCULATOR = "Google Cloud Platform Pricing Calculator";
    }

    public static class GoogleCloudPriceCalculatorSubjects {
        public static final String PRICE_CALCULATOR_COMPUTE_ENGINE = "COMPUTE ENGINE";
    }

    public static class GoogleCloudComputeEngineParamNames {
        public static final String NUMBER_OF_INSTANCES = "numberOfInstances";
        public static final String OS_SOFTWARE = "OSSoftware";
        public static final String VM_CLASS = "VMClass";
        public static final String VM_SERIES = "VMSeries";
        public static final String INSTANCE_TYPE = "instanceType";
        public static final String NUMBER_OF_GPU = "numberOfGPU";
        public static final String GPU_TYPE = "GPUType";
        public static final String LOCAL_SSD = "localSSD";
        public static final String DATACENTER_LOCATION = "datacenterLocation";
        public static final String COMMITTED_USAGE = "committedUsage";
    }

    public static class GoogleCloudComputeEngineTestDataNames {
        public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.numberOfInstances";
        public static final String TESTDATA_OS_SOFTWARE = "testdata.OSSoftware";
        public static final String TESTDATA_VM_CLASS = "testdata.VMClass";
        public static final String TESTDATA_VM_SERIES = "testdata.VMSeries";
        public static final String TESTDATA_INSTANCE_TYPE = "testdata.instanceType";
        public static final String TESTDATA_NUMBER_OF_GPU = "testdata.numberOfGPU";
        public static final String TESTDATA_GPU_TYPE = "testdata.GPUType";
        public static final String TESTDATA_LOCAL_SSD = "testdata.localSSD";
        public static final String TESTDATA_DATACENTER_LOCATION = "testdata.datacenterLocation";
        public static final String TESTDATA_COMMITTED_USAGE = "testdata.committedUsage";
        public static final String TESTDATA_EXPECTED_TOTAL_COST = "testdata.expectedTotalCost";
    }

    public static class GoogleCloudComputeEngineFilterLocatorDynamicParts {
        public static final String OS_FILTER_LOCATOR_PART = "os";
        public static final String VM_CLASS_FILTER_LOCATOR_PART = "class";
        public static final String VM_SERIES_FILTER_LOCATOR_PART = "series";
        public static final String INSTANCE_TYPE_FILTER_LOCATOR_PART = "instance";
        public static final String NUMBER_OF_GPU_FILTER_LOCATOR_PART = "gpuCount";
        public static final String GPU_TYPE_FILTER_LOCATOR_PART = "gpuType";
        public static final String LOCAL_SSD_FILTER_LOCATOR_PART = "ssd";
        public static final String DATACENTER_LOCATION_FILTER_LOCATOR_PART = "location";
        public static final String COMMITTED_USAGE_FILTER_LOCATOR_PART = "cud";
    }
}
