package framework.waits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static framework.constants.Constants.TimeVariables.*;

public class Waiter {
    private final WebDriver driver;
    private final String debugMessage = "DEBUG: %s is not %s";
    private final Logger logger = LogManager.getRootLogger();


    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsDisplayed(String elementLocator, String elementValueAndName) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_10_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementLocator)));
        } catch (TimeoutException e) {
            logger.error(String.format((debugMessage) + "%n", elementValueAndName, "displayed on " +
                    "the page"));
        }
    }

    public void waitUntilElementIsDisplayed(WebElement element, String elementValueAndName) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_10_SECONDS)).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error(String.format((debugMessage) + "%n", elementValueAndName, "displayed on " +
                    "the page"));
        }
    }

    public void waitUntilElementIsClickable(WebElement element, String elementName) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_10_SECONDS)).until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            logger.error(String.format((debugMessage) + "%n", elementName, "clickable"));
        }
    }

    public WebDriver switchToFrame(WebElement frame) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_10_SECONDS)).until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(frame));
    }
}
