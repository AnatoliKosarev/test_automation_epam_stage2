package framework.googleCloudPriceCalculatorApp.page;

import framework.common.screenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class TempMailPage extends AbstractPage {
    private final String returnPageHandler;

    @FindBy(id = "address")
    private WebElement copyEmailAddressButton;

    @FindBy(xpath = "//ul/li[1]")
    private WebElement mailMessageButton;

    @FindBy(xpath = "//tr[@id = 'mobilepadding']//h2[contains(text(), 'Estimated Monthly Cost:')]")
    private WebElement totalCostElement;

    @FindBy(xpath = "//html/..//iframe")
    private WebElement frame;

    @FindBy(xpath = "//span[contains(text(), '40 MB')]")
    private WebElement emailSize;

    public TempMailPage(WebDriver driver, String returnPageHandler) {
        super(driver);
        this.returnPageHandler = returnPageHandler;
    }

    public PriceCalculatorComputeEngineEstimateResultPage copyGeneratedTemporaryEmailAddress() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.navigate().refresh();
        if (!emailSize.isDisplayed()) {
            driver.navigate().refresh();
        }
        waiter.waitUntilElementIsDisplayed(emailSize, "'Email container size' element");
        waiter.waitUntilElementIsClickable(copyEmailAddressButton, "'Copy email address' button");
        screenshotUtils.takeAndSaveScreenshot("copyGeneratedTemporaryEmailAddress");
        copyEmailAddressButton.click();
        PriceCalculatorComputeEngineEstimateResultPage.tempEmailAddress = getCopiedValue();
        logger.info(PriceCalculatorComputeEngineEstimateResultPage.tempEmailAddress + " temporary" +
                " email address is copied");
        driver.switchTo().window(returnPageHandler);
        return new PriceCalculatorComputeEngineEstimateResultPage(driver);
    }

    public PriceCalculatorComputeEngineEstimateResultPage getEmailEstimateCost() {
        driver.navigate().refresh();
        if (!mailMessageButton.isDisplayed()) {
            driver.navigate().refresh();
        }
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
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
          e.getLocalizedMessage();
        }
        return value;
    }
}
