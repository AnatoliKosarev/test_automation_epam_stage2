package framework.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static framework.constants.Constants.GoogleCloudComputeEngineParamNames.*;

public class PriceCalculatorComputeEngineEstimateResultPage extends AbstractPage {
    private static Map<String, String> computeEngineParamList;
    private static ArrayList<String> tabList;
    private static String estimateResultPageHandler;
    private static String tempEmailServiceURL;
    public static String tempEmailAddress;
    public static String emailEstimateTotalCost;
    private boolean computeEngineEstimateIsCorrect = false;
    private List<WebElement> computeEngineEstimateList;
    private final String computeEngineEstimateLocator = "//md-list[@class = 'cartitem ng-scope']";
    private final String estimateComputedParamValueLocator = "//div[@class = 'md-list-item-text " + "ng"
            + "-binding' and contains(text(), '%s')]";
    private final String estimateNumberOfInstancesBaseLocator = "//span[@class = 'ng-binding " +
            "ng-scope' and contains(text(), '%s')]";
    private final String estimateTotalCostLocator = "//h2[@class = 'md-title']/b[@class = 'ng-binding' "
            + "and contains(text(), '%s') and contains(text(), 'per 1 month')]";

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@ng-model = 'emailQuote.user.email']")
    private WebElement emailAddressInputField;

    @FindBy(xpath = "//iframe[@src = '/products/calculator" +
            "/index_ad8ca20a6d1799e286a0c0839aeb86ca523afe927b04501d8ba77dc59e5b8523.frame']")
    private WebElement frame1;

    @FindBy(id = "myFrame")
    private WebElement frame2;

    @FindBy(xpath = "//button[@aria-label = 'Send Email']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//h2[@class = 'md-title']/b[@class = 'ng-binding']")
    private WebElement estimatePageTotalCostElement;

    @FindBy(xpath = "//h2[contains(text(), 'Estimate')]")
    private WebElement estimateHeader;

    public PriceCalculatorComputeEngineEstimateResultPage(WebDriver driver) {
        super(driver);
    }

    public PriceCalculatorComputeEngineEstimateResultPage(WebDriver driver, Map<String
            , String> computeEngineParamList) {
        super(driver);
        PriceCalculatorComputeEngineEstimateResultPage.computeEngineParamList = computeEngineParamList;
    }

    public boolean validateCorrectnessOfEstimateCalculation(String expectedCostValue) {
        boolean result = true;
        scrollElementToView(estimatePageTotalCostElement);
        waiter.waitUntilElementIsDisplayed(computeEngineEstimateLocator, "'Compute engine' " +
                "estimate");
        computeEngineEstimateList = driver.findElements(By.xpath(computeEngineEstimateLocator));
        if (computeEngineEstimateList.size() == 1) {
            if (elementIsPresentOnEstimateBlock(estimateTotalCostLocator, expectedCostValue,
                    "expected estimate total cost")) {
                for (Map.Entry<String, String> entry : computeEngineParamList.entrySet()) {
                    String paramName = entry.getKey();
                    String paramValue = entry.getValue();
                    switch (paramName) {
                        case NUMBER_OF_INSTANCES:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateNumberOfInstancesBaseLocator,
                                            paramValue, "expected number of instances param");
                            break;
                        case VM_CLASS:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateComputedParamValueLocator,
                                            paramValue.toLowerCase(), "expected VM class param");
                            break;
                        case INSTANCE_TYPE:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateComputedParamValueLocator,
                                            formatValue(paramValue), "expected instance type param");
                            break;
                        case DATACENTER_LOCATION:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateComputedParamValueLocator,
                                            formatValue(paramValue), "expected datacenter location param");
                            break;
                        case LOCAL_SSD:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateComputedParamValueLocator,
                                            formatValue(paramValue), "expected local SSD param");
                            break;
                        case COMMITTED_USAGE:
                            result =
                                    result && elementIsPresentOnEstimateBlock(estimateComputedParamValueLocator,
                                            paramValue, "expected committed usage param");
                            break;
                    }
                }
                if (result) {
                    computeEngineEstimateIsCorrect = true;
                }
            }
        }
        return computeEngineEstimateIsCorrect;
    }

    public PriceCalculatorComputeEngineEstimateResultPage displayEmailEstimateEntryForm() {
        waiter.waitUntilElementIsClickable(emailEstimateButton, "'Email estimate' button");
        scrollElementToView(emailEstimateButton);
        emailEstimateButton.click();
        takeAndSaveScreenshot();
        return this;
    }

    public TempMailPage generateTemporaryEmailAddress(String tempEmailServiceURL) {
        estimateResultPageHandler = driver.getWindowHandle();
        PriceCalculatorComputeEngineEstimateResultPage.tempEmailServiceURL = tempEmailServiceURL;
        openNewTab();
        switchToSpecificTabContaining("about:blank");
        navigateToURL(tempEmailServiceURL);
        return new TempMailPage(driver, estimateResultPageHandler);
    }

    public PriceCalculatorComputeEngineEstimateResultPage enterEmailAddressIntoEmailEstimateEntryForm() {
        waiter.switchToFrame(frame1);
        waiter.switchToFrame(frame2);
        scrollElementToView(emailAddressInputField);
        takeAndSaveScreenshot();
        waiter.waitUntilElementIsDisplayed(emailAddressInputField, "'Email address' input field");
        emailAddressInputField.sendKeys(tempEmailAddress);
        takeAndSaveScreenshot();
        return this;
    }

    public TempMailPage sendEstimateEmail() {
        waiter.waitUntilElementIsClickable(sendEmailButton, "'Send email' button");
        sendEmailButton.click();
        switchToSpecificTabContaining(tempEmailServiceURL);
        return new TempMailPage(driver, estimateResultPageHandler);
    }

    public boolean validateEmailEstimateTotalCostIsTheSameAsOnEstimateResultPage() {
        waiter.switchToFrame(frame1);
        waiter.switchToFrame(frame2);
        scrollElementToView(estimateHeader);
        waiter.waitUntilElementIsDisplayed(estimatePageTotalCostElement, "'Estimate page total cost' " +
                "value");
        String estimatePageTotalCost = estimatePageTotalCostElement.getText().replaceFirst("Total" +
                " Estimated Cost: ", "").replaceFirst(" per 1 month", "");
        logger.info("Estimate page total cost: " + estimatePageTotalCost);
        takeAndSaveScreenshot();
        return estimatePageTotalCost.equals(emailEstimateTotalCost);
    }

    private boolean elementIsPresentOnEstimateBlock(String paramLocator, String elementValue,
                                                    String elementName) {
        String finalElementLocator = String.format(paramLocator, elementValue);
        String elementValueAndName = "'" + elementValue + "' " + elementName;
        waiter.waitUntilElementIsDisplayed(finalElementLocator, elementValueAndName);
        return driver.findElement(By.xpath(finalElementLocator)).isDisplayed();
    }

    private String formatValue(String valueToSplit) {
        String[] valueArray = valueToSplit.split("\\s");
        return valueArray[0];
    }

    private void openNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open();");
        tabList = new ArrayList<>(driver.getWindowHandles());
    }

    private void navigateToURL(String url) {
        driver.get(url);
    }

    private void switchToSpecificTabContaining(String url) {
        if (!tabList.isEmpty()) {
            for (String pageHandle : tabList) {
                driver.switchTo().window(pageHandle);
                String tempUrl = driver.getCurrentUrl();
                if (tempUrl.contains(url)) {
                    return;
                }
            }
            logger.error("Specific tab wasn't found in tab list.");
        } else {
            logger.error("Tab list is empty");
        }
    }
}
