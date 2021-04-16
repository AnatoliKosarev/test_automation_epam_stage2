package framework.googleCloudPriceCalculatorApp.page;

import framework.driver.*;
import framework.waits.Waiter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected Waiter waiter;
    protected final Logger logger = LogManager.getRootLogger();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
    }

    public String buildFinalLocator(String baseLocator, String dynamicLocatorPart) {
        return String.format(baseLocator, dynamicLocatorPart);
    }

    public void scrollElementToTheMiddleOfThePage(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", " +
                "inline: \"nearest\"})", element);
    }

    public void scrollElementToView(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollToPageTop() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
    }

    public void takeAndSaveScreenshot() {
        logger.info("Start taking screenshot");
        String screenshotName = getCurrentTimeAsString() + ".png";
        File screenCapture = ((TakesScreenshot)DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture,
                    new File("build/screenshots/" + screenshotName));
            logger.info("Screenshot {} is being saved in build/screenshots", screenshotName);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
