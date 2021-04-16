package framework.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
        takeAndSaveScreenshot();
        copyEmailAddressButton.click();
        takeAndSaveScreenshot();
        PriceCalculatorComputeEngineEstimateResultPage.tempEmailAddress = getCopiedValue();

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
