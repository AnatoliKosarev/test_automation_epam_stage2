package webdriver.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
    private final WebDriver driver;
    public final int WAIT_TIMEOUT_5_SECONDS = 5;
    private final String debugMessage = "DEBUG: %s is not %s";


    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsDisplayed(String elementLocator, String elementValueAndName) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_5_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementLocator)));
        } catch (TimeoutException e) {
            System.out.printf((debugMessage) + "%n", elementValueAndName, "displayed on page");
        }
    }

    public void waitUntilElementIsClickable(WebElement element, String elementName) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_5_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            System.out.printf((debugMessage) + "%n", elementName, "clickable");
        }
    }

    public boolean cookieMessageIsDisplayed(WebElement element) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_5_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
