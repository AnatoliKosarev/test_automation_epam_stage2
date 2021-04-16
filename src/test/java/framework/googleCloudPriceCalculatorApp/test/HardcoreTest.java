package framework.googleCloudPriceCalculatorApp.test;

import framework.googleCloudPriceCalculatorApp.model.EngineEntity;
import framework.googleCloudPriceCalculatorApp.page.HomePage;
import framework.googleCloudPriceCalculatorApp.service.EngineEntityCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static framework.constants.Constants.GoogleCloudComputeEngineURLs.*;

public class HardcoreTest extends CommonConditions{

    @Test
    public void googleCloudPriceEstimateTotalMonthlyCostInEmailIsTheSameAsOnEstimateResultPage() {
        EngineEntity engineEntity = EngineEntityCreator.createEngineEntity();

        boolean emailEstimateIsCalculatedCorrectly = new HomePage(driver)
                .openPage(GOOGLE_CLOUD_HOME_URL)
                .performSearchWithSearchValue("Google Cloud Platform Pricing Calculator")
                .selectLinkOnSearchResultsPage("Google Cloud Platform Pricing Calculator")
                .selectSubjectForPriceCalculation("COMPUTE ENGINE")
                .enterEngineEstimateParameters(engineEntity)
                .addInstanceToEstimate()
                .displayEmailEstimateEntryForm()
                .generateTemporaryEmailAddress(TEMP_MAIL_HOME_URL)
                .copyGeneratedTemporaryEmailAddress()
                .enterEmailAddressIntoEmailEstimateEntryForm()
                .sendEstimateEmail()
                .getEmailEstimateCost()
                .validateEmailEstimateTotalCostIsTheSameAsOnEstimateResultPage();

        System.out.println("HARDCORE TEST DONE");

        Assert.assertTrue(emailEstimateIsCalculatedCorrectly, "Estimate total cost doesn't match " +
                "with emailed estimate total cost.");
    }
}
