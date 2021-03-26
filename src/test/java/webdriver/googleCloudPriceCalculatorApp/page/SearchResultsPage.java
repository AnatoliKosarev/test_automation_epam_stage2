package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {

    private final String searchLinkBaseLocator = "//div[@class = 'gs-title']/a[@class = 'gs-title' " +
            "and contains(., '%s')]";

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectLinkOnSearchResultsPage(String searchLinkText) {
        String finalSearchLinkLocator = buildFinalSearchLinkLocator(searchLinkText);
        waiter.waitUntilElementIsDisplayed(finalSearchLinkLocator, "'" + searchLinkText + "' link");
        driver.findElement(By.xpath(finalSearchLinkLocator)).click();
        return new PriceCalculatorComputeEngineParameterEntryPage(driver);
    }

    private String buildFinalSearchLinkLocator(String searchLinkText) {
        return String.format(searchLinkBaseLocator, searchLinkText);
    }
}
