package framework.googleCloudPriceCalculatorApp.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty(
            "environment"));

    public static String getTestData(String testDataParamName) {
        return resourceBundle.getString(testDataParamName);
    }
}
