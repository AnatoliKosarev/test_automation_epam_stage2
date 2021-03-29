package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    private String languageToggle = "//*[@class = 'devsite-select-toggle-label']";

    @FindBy(className = "devsite-search-container")
    private WebElement searchIcon;

    @FindBy(name = "q")
    private WebElement searchInputField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openPage(String homePageURL) {
        driver.get(homePageURL);
        waiter.waitUntilElementIsDisplayed(languageToggle, "'Language' toggle");
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
