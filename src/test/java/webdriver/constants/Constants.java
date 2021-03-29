package webdriver.constants;

public class Constants {
    public static class TimeVariables {
        public static final int WAIT_TIMEOUT_5_SECONDS = 5;
    }

    public static class PasteBinURLs {
        public static final String PASTEBIN_HOMEPAGE_URL = "https://pastebin.com";
    }

    public static class GoogleCloudComputeEngineURLs {
        public static final String GOOGLE_CLOUD_HOME_URL = "https://cloud.google.com/";
        public static final String TEMP_MAIL_HOME_URL = "https://mail.tm/en/";
    }

    public static class PasteBinParamNames {
        public static final String CODE_PARAM_NAME = "code";
        public static final String EXP_DATE_PARAM_NAME = "expirationPeriod";
        public static final String TITLE_PARAM_NAME = "title";
        public static final String SYNTAX_TYPE_PARAM_NAME = "syntaxType";
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
