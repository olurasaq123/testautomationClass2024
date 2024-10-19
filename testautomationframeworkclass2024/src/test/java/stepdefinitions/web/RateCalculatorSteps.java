package stepdefinitions.web;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.RateCalculatorPage;
import utilities.ConfigUtil;
import utilities.WebDriverHelpers;

import java.io.IOException;
import java.io.ObjectInputFilter;

public class RateCalculatorSteps {

    private WebDriverHelpers webDriverHelpers;
    private RateCalculatorPage rateCalculatorPage;
    private ConfigUtil configUtil;

    public RateCalculatorSteps(WebDriverHelpers _webDriverHelpers, RateCalculatorPage _rateCalculatorPage, ConfigUtil _configUtil){

        this.webDriverHelpers = _webDriverHelpers;
        this.rateCalculatorPage = _rateCalculatorPage;
        this.configUtil = _configUtil;
    }

    @Given("that a user loads an application under test")
    public void thatAUserLoadsAnApplicationUnderTest() throws IOException {

        webDriverHelpers.driver.get(configUtil.loadEnvProfileData("baseUrl"));
       // Thread.sleep(Duration.ofMinutes(1));
       // webDriverHelpers.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }


    @When("a user inputs {int} into GBP text field")
    public void aUserInputsIntoGBPTextField(int currencyValue) throws InterruptedException {

        rateCalculatorPage.enterCurrencyGBPValue(String.valueOf(currencyValue));
    }

    @Then("a user sees {double} value in NGN text field")
    public void aUserSeesValueInNGNTextField(double currencyValueNGN) throws InterruptedException {
        String expectedCurrencyValue = String.valueOf(currencyValueNGN);
        String actualCurrencyValue =  String.valueOf(rateCalculatorPage.enterCurrencyNGNValue());
        Assert.assertEquals(expectedCurrencyValue, actualCurrencyValue);
    }

    @When("a user clicks on Send Now button")
    public void aUserClicksOnSendNowButton() {

        rateCalculatorPage.clickOnSendNowButton();
    }


    @And("a user selects {string} as the bank option")
    public void aUserSelectsAsTheBankOption(String bankName) {

        rateCalculatorPage.selectBankFromDropDown(bankName);
    }

    @And("a user inputs {string} as the account number")
    public void aUserInputsAsTheAccountNumber(String accountNumber) {

        rateCalculatorPage.enterAccountNumber(accountNumber);
    }

    @And("a user clicks on Send button")
    public void aUserClicksOnSendButton() {

        rateCalculatorPage.clickOnSendButton();
    }

    @Then("the text {string} message should appear")
    public void theTextMessageShouldAppear(String successMsg) {
        Assert.assertEquals(rateCalculatorPage.getSuccessMessage(), successMsg);
    }
}
