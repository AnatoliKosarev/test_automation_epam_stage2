package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import webdriver.waits.Waiter;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected Waiter waiter;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
    }

    public String buildFinalLocator(String baseLocator, String dynamicLocatorPart) {
        return String.format(baseLocator, dynamicLocatorPart);
    }
}
