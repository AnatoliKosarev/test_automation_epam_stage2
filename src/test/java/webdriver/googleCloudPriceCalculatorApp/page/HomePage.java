package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static webdriver.constants.Constants.TimeVariables.*;

public class HomePage extends AbstractPage {

    @FindBy(className = "devsite-search-container")
    private WebElement searchIcon;

    @FindBy(name = "q")
    private WebElement searchInputField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openPage(String homePageURL) {
        driver.get(homePageURL);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_5_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class = 'devsite-select-toggle-label']")));
        return this;
    }

    public SearchResultsPage performSearchWithSearchValue(String searchValue) {
        waiter.waitUntilElementIsClickable(searchIcon, "Search icon");
        searchIcon.click();
        waiter.waitUntilElementIsDisplayed(searchInputField, "Search input field");
        searchInputField.sendKeys(searchValue);
        searchInputField.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
