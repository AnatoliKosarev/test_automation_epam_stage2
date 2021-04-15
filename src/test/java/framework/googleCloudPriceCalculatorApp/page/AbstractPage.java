package framework.googleCloudPriceCalculatorApp.page;

import framework.waits.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

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
}
