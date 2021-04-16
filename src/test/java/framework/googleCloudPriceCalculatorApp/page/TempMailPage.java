package framework.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TempMailPage extends AbstractPage {
    private final String returnPageHandler;
    private String emailEstimateTotalCost;
    private String emailSizeLocator = "//span[contains(text(), '40 MB')]";

    @FindBy(id = "address")
    private WebElement copyEmailAddressButton;

    @FindBy(xpath = "//ul/li[1]")
    private WebElement mailMessageButton;

    @FindBy(xpath = "//tr[@id = 'mobilepadding']//h2[contains(text(), 'Estimated Monthly Cost:')]")
    private WebElement totalCostElement;

    @FindBy(xpath = "//html/..//iframe")
    private WebElement frame;

    public TempMailPage(WebDriver driver, String returnPageHandler) {
        super(driver);
        this.returnPageHandler = returnPageHandler;
    }

    public PriceCalculatorComputeEngineEstimateResultPage copyGeneratedTemporaryEmailAddress() {
        waiter.waitUntilElementIsDisplayed(emailSizeLocator, "'Email container size' element");
        waiter.waitUntilElementIsClickable(copyEmailAddressButton, "'Copy email address' button");
        copyEmailAddressButton.click();
        driver.switchTo().window(returnPageHandler);
        return new PriceCalculatorComputeEngineEstimateResultPage(driver);
    }

    public PriceCalculatorComputeEngineEstimateResultPage getEmailEstimateCost() {
        driver.navigate().refresh();
        waiter.waitUntilElementIsDisplayed(mailMessageButton, "'Mail message' button");
        takeAndSaveScreenshot();
        mailMessageButton.click();
        waiter.switchToFrame(frame);
        waiter.waitUntilElementIsDisplayed(totalCostElement, "'Estimate total cost' element");
        emailEstimateTotalCost = totalCostElement.getText().replaceFirst("Estimated Monthly Cost:" +
                " ", "");
        logger.info("Email total cost: " + emailEstimateTotalCost);
        driver.switchTo().window(returnPageHandler);
        return new PriceCalculatorComputeEngineEstimateResultPage(driver, emailEstimateTotalCost);
    }

}
