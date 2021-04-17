package framework.googleCloudPriceCalculatorApp.page;

import framework.waits.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

import static framework.constants.Constants.TimeVariables.WAIT_TIMEOUT_10_SECONDS;

public class TempMailPage extends AbstractPage {
    private final String returnPageHandler;
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
        driver.navigate().refresh();
        waiter.waitUntilElementIsDisplayed(emailSizeLocator, "'Email container size' element");
        waiter.waitUntilElementIsClickable(copyEmailAddressButton, "'Copy email address' button");
        copyEmailAddressButton.click();
        PriceCalculatorComputeEngineEstimateResultPage.tempEmailAddress = getCopiedValue();
        logger.info(PriceCalculatorComputeEngineEstimateResultPage.tempEmailAddress + " temporary" +
                " email address is copied");
        return new PriceCalculatorComputeEngineEstimateResultPage(driver);
    }

    public PriceCalculatorComputeEngineEstimateResultPage getEmailEstimateCost() {
        driver.navigate().refresh();
        waiter.waitUntilElementIsDisplayed(mailMessageButton, "'Mail message' button");
        mailMessageButton.click();
        waiter.switchToFrame(frame);
        waiter.waitUntilElementIsDisplayed(totalCostElement, "'Estimate total cost' element");
        PriceCalculatorComputeEngineEstimateResultPage.emailEstimateTotalCost =
                totalCostElement.getText().replaceFirst("Estimated Monthly Cost: ", "");
        logger.info("Email total cost: " + PriceCalculatorComputeEngineEstimateResultPage.emailEstimateTotalCost);
        driver.switchTo().window(returnPageHandler);
        return new PriceCalculatorComputeEngineEstimateResultPage(driver);
    }

    private String getCopiedValue() {
        String value = "";
        try {
            value =
                    (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            driver.switchTo().window(returnPageHandler);
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
          e.getLocalizedMessage();
        }
        return value;
    }
}
