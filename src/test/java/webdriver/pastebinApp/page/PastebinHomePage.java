package webdriver.pastebinApp.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.waits.CustomConditions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static webdriver.constants.Constants.PasteBinParamNames.*;
import static webdriver.constants.Constants.PasteBinURLs.*;
import static webdriver.constants.Constants.TimeVariables.*;

public class PastebinHomePage extends AbstractPastebinPage {
    private final String expirationOptionLocator = "//*[@id='select2-postform-expiration-results" +
            "']/li" + "[contains(., '%s')]";
    private final String syntaxTypeOptionLocator = "//*[@class= 'select2-results__options " +
            "select2-results__options--nested']/li[contains(text(), '%s')]";

    @FindBy(id = "postform-text")
    private WebElement codeInputField;

    @FindBy(id = "postform-name")
    private WebElement titleInputField;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationFilter;

    @FindBy(id = "select2-postform-format-container")
    private WebElement pasteSyntaxTypeFilter;

    @FindBy(xpath = "//*[@class = 'btn -big' and contains(., 'Create New Paste')]")
    private WebElement savePasteButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
        initPasteParamList();
    }

    private void initPasteParamList() {
        if (pasteParamsList == null) {
            pasteParamsList = new HashMap<>();
            pasteParamsList.put(EXP_DATE_PARAM_NAME, null);
            pasteParamsList.put(CODE_PARAM_NAME, null);
            pasteParamsList.put(TITLE_PARAM_NAME, null);
            pasteParamsList.put(SYNTAX_TYPE_PARAM_NAME, null);
        }
    }

    private void addPasteParamToPasteParamsList(String paramName, String paramValue) {
        for (Map.Entry<String, String> entry : pasteParamsList.entrySet()) {
            if (entry.getKey().equals(paramName)) {
                entry.setValue(paramValue);
                return;
            }
        }
    }

    public PastebinHomePage openPage() {
        driver.get(PASTEBIN_HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_5_SECONDS)).until(CustomConditions.jQueryAJAXsCompleted());
        if (waiter.cookieMessageIsDisplayed(acceptCookieButton)) {
            acceptCookieButton.click();
        }
        return this;
    }

    public PastebinHomePage enterPasteCode(String codeParamValue) {
        addPasteParamToPasteParamsList(CODE_PARAM_NAME, codeParamValue);
        codeInputField.sendKeys(codeParamValue);
        return this;
    }

    public PastebinHomePage selectPasteExpirationValue(String expirationPeriodParamValue) {
        addPasteParamToPasteParamsList(EXP_DATE_PARAM_NAME, expirationPeriodParamValue);
        expandFilterPanel(pasteExpirationFilter, "'Paste expiration' filter");
        selectOptionOnFilterPanel(buildFinalLocator(expirationOptionLocator, expirationPeriodParamValue),
                "Entered expiration value '" + expirationPeriodParamValue + "'");
        return this;
    }

    public PastebinHomePage selectPasteSyntaxType(String syntaxTypeParamValue) {
        addPasteParamToPasteParamsList(SYNTAX_TYPE_PARAM_NAME, syntaxTypeParamValue);
        expandFilterPanel(pasteSyntaxTypeFilter, "'Paste syntax type' filter");
        selectOptionOnFilterPanel(buildFinalLocator(syntaxTypeOptionLocator,
                syntaxTypeParamValue), "Entered syntax type value '" + syntaxTypeParamValue + "'");
        return this;
    }

    public PastebinHomePage enterPasteTitle(String titleParamValue) {
        addPasteParamToPasteParamsList(TITLE_PARAM_NAME, titleParamValue);
        titleInputField.sendKeys(titleParamValue);
        return this;
    }


    public PastebinCreatedPasteViewPage savePaste() {
        waiter.waitUntilElementIsClickable(savePasteButton, "Save paste button");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", savePasteButton);
        savePasteButton.click();
        return new PastebinCreatedPasteViewPage(driver, pasteParamsList);
    }

    private void expandFilterPanel(WebElement filter, String filterName) {
        waiter.waitUntilElementIsClickable(filter, filterName);
        filter.click();
    }

    private void selectOptionOnFilterPanel(String optionLocator, String optionValueAndNAme) {
        waiter.waitUntilElementIsDisplayed(optionLocator, optionValueAndNAme);
        driver.findElement(By.xpath(optionLocator)).click();
    }
}
