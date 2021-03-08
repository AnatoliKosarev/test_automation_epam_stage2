package webdriver.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webdriver.page.PastebinHomePage;

public class BringItOnTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void pasteIsCreatedSuccessfullyWithTitleCodeSyntaxParams() {
        boolean pasteCreatedSuccessfully = new PastebinHomePage(driver)
                .openPage()
                .enterPasteCode("git config --global user.name  \"New Sheriff in Town\"\n" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" + "git push origin master --force")
                .selectPasteSyntaxType("Bash")
                .selectPasteExpirationValue("10 Minutes")
                .enterPasteTitle("how to gain dominance among developers")
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
