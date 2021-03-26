package webdriver.googleCloudPriceCalculatorApp.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webdriver.googleCloudPriceCalculatorApp.page.HomePage;

import static webdriver.constants.Constants.URLs.*;

public class HurtMePlentyTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void googleCloudPriceEstimateIsCalculatedCorrectly() {
        boolean estimateIsCalculatedCorrectly = new HomePage(driver)
                .openPage(GOOGLE_CLOUD_HOME_URL)
                .performSearchWithSearchValue("Google Cloud Platform Pricing Calculator")
                .selectLinkOnSearchResultsPage("Google Cloud Platform Pricing Calculator")
                .selectSubjectForPriceCalculation("COMPUTE ENGINE")
                .enterNumberOfInstancesParameter("4")
                .selectOperatingSystemSoftwareParameters("Free: Debian, CentOS, CoreOS, Ubuntu, " +
                        "or other User Provided OS")
                .selectMachineClassParameter("Regular")
                .selectMachineSeriesParameter("N1")
                .selectMachineTypeParameter("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .selectAddGPUsCheckbox()
                .selectNumberOfGPUsParameter("1")
                .selectGPUTypeParameter("NVIDIA Tesla V100")
                .selectLocalSSDParameter("2x375 GB")
                .selectDatacenterLocation("Frankfurt (europe-west3)")
                .selectCommittedUsageParameter("1 Year")
                .addInstanceToEstimate()
                .validateCorrectnessOfEstimateCalculation("USD 1,082.77");

        Assert.assertTrue(estimateIsCalculatedCorrectly, "Estimate wasn't created successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
