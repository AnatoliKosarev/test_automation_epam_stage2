package webdriver.pastebinApp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriver.waits.Waiter;

import java.util.Map;

public abstract class AbstractPastebinPage {
    protected WebDriver driver;
    protected Map<String, String> pasteParamsList;
    protected final Waiter waiter;

    @FindBy(xpath = "//button[@class = 'sc-ifAKCX ljEJIv' and contains(., 'AGREE')]")
    protected WebElement acceptCookieButton;

    public AbstractPastebinPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
    }

    public AbstractPastebinPage(WebDriver driver, Map<String, String> pasteParamsList) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.pasteParamsList = pasteParamsList;
        PageFactory.initElements(driver, this);
    }

    public String buildFinalLocator(String baseLocator, String dynamicLocatorPart) {
        return String.format(baseLocator, dynamicLocatorPart);
    }
}
