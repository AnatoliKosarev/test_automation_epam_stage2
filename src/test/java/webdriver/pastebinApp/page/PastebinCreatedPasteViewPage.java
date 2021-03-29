package webdriver.pastebinApp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.waits.CustomConditions;

import java.time.Duration;
import java.util.Map;

import static webdriver.constants.Constants.PasteBinParamNames.*;
import static webdriver.constants.Constants.TimeVariables.*;

public class PastebinCreatedPasteViewPage extends AbstractPastebinPage {
    private boolean pasteCreatedCorrectly = false;
    private final String savedPasteTitleValueLocator = "//div[@class = 'info-top' and contains(.,"
            + " '%s')]";
    private final String savedPasteExpirationValueLocator = "//div[@class = 'expire' and " +
            "contains(text(), '%s')]";
    private final String savedPasteCodeValueBaseLocator = "//textarea[@class = 'textarea'%s]";
    private final String savedPasteCodeValueContainsPartLocator = " and contains(text(), '%s')";
    private final String savedPasteSyntaxTypeValueLocator = "//a[@class = 'btn -small h_800' and "
            + "contains(text(), '%s')]";
    private final String pasteCreatedSuccessfullyMessageLocator = "//div[@class = 'notice " +
            "-success -post-view']";

    public PastebinCreatedPasteViewPage(WebDriver driver, Map<String, String> pasteParamsList) {
        super(driver, pasteParamsList);
        PageFactory.initElements(driver, this);
    }

    public boolean validateCorrectnessOfCreatedPaste() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_5_SECONDS)).until(CustomConditions.jQueryAJAXsCompleted());
        if (waiter.cookieMessageIsDisplayed(acceptCookieButton)) {
            acceptCookieButton.click();
        }
        boolean result = true;
        if (pasteCreatedSuccessfullyMessageIsPresentOnPasteViewPage(pasteCreatedSuccessfullyMessageLocator, "Success message")) {
            for (Map.Entry<String, String> entry : pasteParamsList.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();
                if (paramName.equals(TITLE_PARAM_NAME) && paramValue != null) {
                    result = result && savedParamIsPresentOnPasteViewPage(savedPasteTitleValueLocator,
                                    paramValue, "title");
                } else if (paramName.equals(EXP_DATE_PARAM_NAME) && paramValue != null) {
                    String shortExpParamValue = paramValue.substring(0, 6).toLowerCase();
                    result =
                            result && savedParamIsPresentOnPasteViewPage(savedPasteExpirationValueLocator,
                                    shortExpParamValue, "expiration value");
                } else if (paramName.equals(CODE_PARAM_NAME) && paramValue != null) {
                    result =
                            result && savedParamIsPresentOnPasteViewPage(buildCodeValueFinalLocator(paramValue), paramValue, "code value");
                } else if (paramName.equals(SYNTAX_TYPE_PARAM_NAME) && paramValue != null) {
                    result =
                            result && savedParamIsPresentOnPasteViewPage(savedPasteSyntaxTypeValueLocator,
                                    paramValue, "syntax type");
                }
            }
            if (result) {
                pasteCreatedCorrectly = true;
            }
        }
        return pasteCreatedCorrectly;
    }

    private boolean savedParamIsPresentOnPasteViewPage(String paramLocator, String elementValue,
                                                       String elementName) {
        String finalElementLocator = String.format(paramLocator, elementValue);
        String elementValueAndName = "'" + elementValue + "' " + elementName;
        waiter.waitUntilElementIsDisplayed(finalElementLocator, elementValueAndName);
        return driver.findElement(By.xpath(finalElementLocator)).isDisplayed();
    }

    private boolean pasteCreatedSuccessfullyMessageIsPresentOnPasteViewPage(String successMessageLocator, String elementName) {
        waiter.waitUntilElementIsDisplayed(successMessageLocator, elementName);
        return driver.findElement(By.xpath(successMessageLocator)).isDisplayed();
    }

    public String buildCodeValueFinalLocator(String notFormattedCodeValue) {
        StringBuilder locatorPartWithCodeLines = new StringBuilder();
        String[] codeTermsArray = notFormattedCodeValue.split("\\n");
        for (String codeLine : codeTermsArray) {
            locatorPartWithCodeLines.append(String.format(savedPasteCodeValueContainsPartLocator,
                    codeLine));
        }
        return String.format(savedPasteCodeValueBaseLocator, locatorPartWithCodeLines.toString());
    }
}
