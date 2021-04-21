package framework.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private final Logger logger = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        String failedMethodName = result.getName();
        logger.info("Test {} - FAILED!", failedMethodName);
        screenshotUtils.takeAndSaveScreenshot(failedMethodName);
    }
}
