package framework.googleCloudPriceCalculatorApp.test;

import framework.driver.*;
import framework.common.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

import static framework.common.Config.CLEAR_SCREENSHOT_DIR;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected static final Logger LOGGER = LogManager.getRootLogger();

    static {
        LOGGER.info("START TIME: " + LocalTime.now());
        if (CLEAR_SCREENSHOT_DIR) {
            File screenshotDirectory = new File("build/screenshots");
            if (screenshotDirectory.isDirectory()) {
                LOGGER.info("Clearing build/screenshots directory");
                for (File screenshotFile : Objects.requireNonNull(screenshotDirectory.listFiles())) {
                    screenshotFile.delete();
                }
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}
