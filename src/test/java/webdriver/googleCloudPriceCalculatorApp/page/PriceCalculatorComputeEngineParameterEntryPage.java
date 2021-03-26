package webdriver.googleCloudPriceCalculatorApp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static webdriver.constants.Constants.GoogleCloudComputeEngineFilterLocatorDynamicParts.*;
import static webdriver.constants.Constants.GoogleCloudComputeEngineParamNames.*;

public class PriceCalculatorComputeEngineParameterEntryPage extends AbstractPage {
    private Map<String, String> computeEngineParamList;
    private final String computeSubjectButtonBaseLocator =
            "//md-tab-item/div/div/div[contains" + "(text" + "(), '%s')]/../..";
    private String optionBaseLocator = "//div[@class= 'md-select-menu-container md-active " + "md" +
            "-clickable']//div[contains(text(), '%s')]";
    private String filterBaseLocator = "//md-select[@ng-model= 'listingCtrl.computeServer.%s']";

    @FindBy(xpath = "//iframe[@src = '/products/calculator" +
            "/index_ad8ca20a6d1799e286a0c0839aeb86ca523afe927b04501d8ba77dc59e5b8523.frame']")
    private WebElement frame1;

    @FindBy(id = "myFrame")
    private WebElement frame2;

    @FindBy(xpath = "//input[@ng-model= 'listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInputField;

    @FindBy(xpath = "//md-checkbox[@ng-model = 'listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUCheckbox;

    @FindBy(xpath = "//button[@ng-click= 'listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    public PriceCalculatorComputeEngineParameterEntryPage(WebDriver driver) {
        super(driver);
        initComputeEngineParamList();
    }

    private void initComputeEngineParamList() {
        if (computeEngineParamList == null) {
            computeEngineParamList = new HashMap<>();
            computeEngineParamList.put(NUMBER_OF_INSTANCES, null);
            computeEngineParamList.put(OS_SOFTWARE, null);
            computeEngineParamList.put(VM_CLASS, null);
            computeEngineParamList.put(VM_SERIES, null);
            computeEngineParamList.put(INSTANCE_TYPE, null);
            computeEngineParamList.put(NUMBER_OF_GPU, null);
            computeEngineParamList.put(GPU_TYPE, null);
            computeEngineParamList.put(LOCAL_SSD, null);
            computeEngineParamList.put(DATACENTER_LOCATION, null);
            computeEngineParamList.put(COMMITTED_USAGE, null);
        }
    }

    private void addValueToComputeEngineParamList(String paramName, String paramValue) {
        for (Map.Entry<String, String> entry : computeEngineParamList.entrySet()) {
            if (entry.getKey().equals(paramName)) {
                entry.setValue(paramValue);
                return;
            }
        }
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectSubjectForPriceCalculation(String computeSubject) {
        String computeSubjectButtonFinalLocator =
                buildFinalComputeSubjectButtonLocator(computeSubject);
        waiter.switchToFrame(frame1);
        waiter.switchToFrame(frame2);
        waiter.waitUntilElementIsDisplayed(computeSubjectButtonFinalLocator,
                "'" + computeSubject + "' button");
        driver.findElement(By.xpath(computeSubjectButtonFinalLocator)).click();
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage enterNumberOfInstancesParameter(String numberOfInstancesValue) {
        if (checkEntryValueIsInteger(numberOfInstancesValue)) {
            addValueToComputeEngineParamList(NUMBER_OF_INSTANCES, numberOfInstancesValue);
            waiter.waitUntilElementIsDisplayed(numberOfInstancesInputField, "'Number of " +
                    "instances' input field");
            numberOfInstancesInputField.sendKeys(numberOfInstancesValue);
        }
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectOperatingSystemSoftwareParameters(String operatingSystemValue) {
        addValueToComputeEngineParamList(OS_SOFTWARE, operatingSystemValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, OS_FILTER_LOCATOR_PART),
                "'Operating system / " + "Software' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, operatingSystemValue),
                "Entered OS / Software type value '" + operatingSystemValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectMachineClassParameter(String machineClassValue) {
        addValueToComputeEngineParamList(VM_CLASS, machineClassValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, VM_CLASS_FILTER_LOCATOR_PART),
                "'Machine class' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, machineClassValue),
                "Entered Machine class value '" + machineClassValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectMachineSeriesParameter(String machineSeriesValue) {
        addValueToComputeEngineParamList(VM_SERIES, machineSeriesValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, VM_SERIES_FILTER_LOCATOR_PART),
                "'Machine series' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, machineSeriesValue),
                "Entered Machine series value '" + machineSeriesValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectMachineTypeParameter(String machineTypeValue) {
        addValueToComputeEngineParamList(INSTANCE_TYPE, machineTypeValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, INSTANCE_TYPE_FILTER_LOCATOR_PART)
                , "'Machine type' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, machineTypeValue),
                "Entered Machine type value '" + machineTypeValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectAddGPUsCheckbox() {
        waiter.waitUntilElementIsDisplayed(addGPUCheckbox, "'Add GPUs' checkbox");
        if(!addGPUCheckbox.isSelected()) {
            addGPUCheckbox.click();
        }
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectNumberOfGPUsParameter(String numberOfGPUValue) {
        if (checkEntryValueIsInteger(numberOfGPUValue)) {
            addValueToComputeEngineParamList(NUMBER_OF_GPU, numberOfGPUValue);
            expandFilterPanel(buildFinalLocator(filterBaseLocator,
                    NUMBER_OF_GPU_FILTER_LOCATOR_PART), "'Number of GPUs' filter");
            selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, numberOfGPUValue),
                    "Entered Number of GPUs value '" + numberOfGPUValue + "'");
        }
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectGPUTypeParameter(String gpuTypeValue) {
        addValueToComputeEngineParamList(GPU_TYPE, gpuTypeValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, GPU_TYPE_FILTER_LOCATOR_PART),
                "'GPU type' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, gpuTypeValue),
                "Entered " + "GPU type value '" + gpuTypeValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectLocalSSDParameter(String localSSDParamValue) {
        addValueToComputeEngineParamList(LOCAL_SSD, localSSDParamValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator, LOCAL_SSD_FILTER_LOCATOR_PART),
                "'Local SSD' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, localSSDParamValue),
                "Entered " + "Local SSD value '" + localSSDParamValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectDatacenterLocation(String datacenterLocationValue) {
        addValueToComputeEngineParamList(DATACENTER_LOCATION, datacenterLocationValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator,
                DATACENTER_LOCATION_FILTER_LOCATOR_PART), "'Datacenter location' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, datacenterLocationValue),
                "Entered Datacenter location value '" + datacenterLocationValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineParameterEntryPage selectCommittedUsageParameter(String committedUsageParamValue) {
        addValueToComputeEngineParamList(COMMITTED_USAGE, committedUsageParamValue);
        expandFilterPanel(buildFinalLocator(filterBaseLocator,
                COMMITTED_USAGE_FILTER_LOCATOR_PART), "'Committed usage' filter");
        selectOptionOnFilterPanel(buildFinalLocator(optionBaseLocator, committedUsageParamValue),
                "Entered Committed usage value '" + committedUsageParamValue + "'");
        return this;
    }

    public PriceCalculatorComputeEngineEstimateResultPage addInstanceToEstimate() {
        waiter.waitUntilElementIsClickable(addToEstimateButton, "'Add to estimate' button");
        addToEstimateButton.click();
        return new PriceCalculatorComputeEngineEstimateResultPage(driver,
                computeEngineParamList);
    }

    private boolean checkEntryValueIsInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            Assert.fail("Entered value '" + value + "' is of invalid format");
            return false;
        }
    }

    private String buildFinalComputeSubjectButtonLocator(String computeSubjectButtonText) {
        StringBuilder formattedComputeSubjectButtonText = new StringBuilder();
        String[] computeSubjectArray = computeSubjectButtonText.split("\\s");
        for (int i = 0; i < computeSubjectArray.length; i++) {
            String subjectPart = computeSubjectArray[i];
            subjectPart = subjectPart.toLowerCase();
            String subjectPartStartWithCap =
                    subjectPart.substring(0, 1).toUpperCase() + subjectPart.substring(1);
            if (i != computeSubjectArray.length - 1) {
                subjectPartStartWithCap += " ";
            }
            formattedComputeSubjectButtonText.append(subjectPartStartWithCap);
        }
        return String.format(computeSubjectButtonBaseLocator,
                formattedComputeSubjectButtonText.toString());
    }

    private void expandFilterPanel(String filterLocator, String filterName) {
        waiter.waitUntilElementIsDisplayed(filterLocator, filterName);
        driver.findElement(By.xpath(filterLocator)).click();
    }

    private void selectOptionOnFilterPanel(String optionLocator, String optionValueAndNAme) {
        waiter.waitUntilElementIsDisplayed(optionLocator, optionValueAndNAme);
        driver.findElement(By.xpath(optionLocator)).click();
    }
}
