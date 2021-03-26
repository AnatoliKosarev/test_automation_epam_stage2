package webdriver.pastebinApp.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webdriver.pastebinApp.page.PastebinHomePage;

public class ICanWinTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void pasteIsCreatedSuccessfullyWithTitleExpirationCodeParams() {
        boolean pasteCreatedSuccessfully = new PastebinHomePage(driver)
                .openPage()
                .enterPasteCode("Hello from WebDriver")
                .selectPasteExpirationValue("10 Minutes")
                .enterPasteTitle("helloweb")
                .savePaste()
                .validateCorrectnessOfCreatedPaste();

        Assert.assertTrue(pasteCreatedSuccessfully, "Paste wasn't created successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
