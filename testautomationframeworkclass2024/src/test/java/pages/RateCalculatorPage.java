package pages;

import org.openqa.selenium.By;
import utilities.WebDriverHelpers;

import java.time.Duration;

public class RateCalculatorPage {


    private WebDriverHelpers webDriverHelpers;

    private By currencyTextBox_GBP = By.xpath("//input[@class='from-currency p-1']");
    private By currencyTextBox_NGN = By.cssSelector(".to-currency.m-top-2.p-1");
    private By sendNowButton = By.xpath("//button[@class='send-now m-top-2']");

    private By selectBankOption = By.xpath("//select[@class='select-bank p-1']");
    private By accountNumberTextBox = By.xpath("//input[@class='account-number p-1 m-top-2']");
    private By sendRateButton = By.xpath("//button[@class='send m-top-2']");
    private By successText = By.xpath("//p[@class='success']");

    public RateCalculatorPage(WebDriverHelpers _webDriverHelper) {

        this.webDriverHelpers = _webDriverHelper;
    }

    public void enterCurrencyGBPValue(String currencyValueGBP) throws InterruptedException {

        webDriverHelpers.driver.findElement(currencyTextBox_GBP).sendKeys(currencyValueGBP);
        webDriverHelpers.driver.findElement(currencyTextBox_GBP).clear();
        Thread.sleep(Duration.ofSeconds(5));
        webDriverHelpers.driver.findElement(currencyTextBox_GBP).sendKeys(currencyValueGBP);


    }

    public double enterCurrencyNGNValue() {

        String actualValue = webDriverHelpers.driver.findElement(currencyTextBox_NGN).getAttribute("value");
        return Double.parseDouble(actualValue);

    }

    public void clickOnSendNowButton() {

        webDriverHelpers.driver.findElement(sendNowButton).click();
    }

    public void selectBankFromDropDown(String bankName) {

        webDriverHelpers.SelectItemFromDropDown(selectBankOption, bankName);
    }

    public void enterAccountNumber(String accountNumber) {

        webDriverHelpers.driver.findElement(accountNumberTextBox).sendKeys(accountNumber);

    }

    public void clickOnSendButton(){
        webDriverHelpers.driver.findElement(sendRateButton).click();
    }

    public String getSuccessMessage(){

        return webDriverHelpers.driver.findElement(successText).getText();
    }

}
