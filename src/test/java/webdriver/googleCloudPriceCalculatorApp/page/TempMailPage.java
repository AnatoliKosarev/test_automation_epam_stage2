package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static webdriver.constants.Constants.TimeVariables.WAIT_TIMEOUT_5_SECONDS;

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
        waiter.waitUntilElementIsDisplayed(mailMessageButton, "'Mail message' button");
        mailMessageButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_5_SECONDS)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        waiter.waitUntilElementIsDisplayed(totalCostElement, "'Estimate total cost' element");
        emailEstimateTotalCost = totalCostElement.getText().replaceFirst("Estimated Monthly Cost:" +
                " ", "");
        System.out.println("DEBUG: Email total cost: " + emailEstimateTotalCost);
        driver.switchTo().window(returnPageHandler);
        return new PriceCalculatorComputeEngineEstimateResultPage(driver, emailEstimateTotalCost);
    }

}
