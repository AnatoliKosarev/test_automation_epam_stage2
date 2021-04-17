package framework.googleCloudPriceCalculatorApp.test;

import framework.googleCloudPriceCalculatorApp.model.EngineEntity;
import framework.googleCloudPriceCalculatorApp.service.EngineEntityCreator;
import framework.googleCloudPriceCalculatorApp.service.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.googleCloudPriceCalculatorApp.page.HomePage;

import static framework.constants.Constants.GoogleCloudComputeEngineTestDataNames.TESTDATA_EXPECTED_TOTAL_COST;
import static framework.constants.Constants.GoogleCloudComputeEngineURLs.*;
import static framework.constants.Constants.GoogleCloudPriceCalculatorSubjects.PRICE_CALCULATOR_COMPUTE_ENGINE;
import static framework.constants.Constants.GoogleCloudServices.GOOGLE_CLOUD_PRICE_CALCULATOR;

public class HurtMePlentyTest extends CommonConditions{

    @Test
    public void googleCloudPriceEstimateIsCalculatedCorrectly() {
        EngineEntity engineEntity = EngineEntityCreator.createEngineEntity();
        LOGGER.info("HURTMEPLENTY test is running");

        boolean estimateIsCalculatedCorrectly = new HomePage(driver)
                .openPage(GOOGLE_CLOUD_HOME_URL)
                .performSearchWithSearchValue(GOOGLE_CLOUD_PRICE_CALCULATOR)
                .selectLinkOnSearchResultsPage(GOOGLE_CLOUD_PRICE_CALCULATOR)
                .selectSubjectForPriceCalculation(PRICE_CALCULATOR_COMPUTE_ENGINE)
                .enterEngineEstimateParameters(engineEntity)
                .addInstanceToEstimate()
                .validateCorrectnessOfEstimateCalculation(TestDataReader.getTestData(TESTDATA_EXPECTED_TOTAL_COST));

        Assert.assertTrue(estimateIsCalculatedCorrectly, "Estimate wasn't created successfully");
    }
}
