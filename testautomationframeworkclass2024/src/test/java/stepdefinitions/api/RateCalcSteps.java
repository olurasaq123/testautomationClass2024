package stepdefinitions.api;

import DataModel.RateDataModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import webservices.RateCalcServices;

import java.util.List;
import java.util.Map;

public class RateCalcSteps {

    private RateCalcServices rateCalcServices;
    Response response;

    private RateDataModel rateDataModel;

    RateDataModel.RateDataModelBuilder rateDataModelBuilder = RateDataModel.builder();

    public RateCalcSteps(RateCalcServices _rateCalcServices){

        this.rateCalcServices = _rateCalcServices;
    }


    @Given("that a user make a Get request to get rate")
    public void thatAUserMakeAGetRequestToGetRate() {

      response =  rateCalcServices.getRate();
    }

    @And("i should get a {int} status code")
    public void iShouldGetAStatusCode(int statusCode) {

        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @Then("i should be able to get a correct rate")
    public void iShouldBeAbleToGetACorrectRate(List<Map<String, String>> rateData) {

        String expectedfromCurrency = rateData.get(0).get("fromCurrency");
       String actualFromCurrency =  response.jsonPath().get("fromCurrency");
        Assert.assertEquals(expectedfromCurrency, actualFromCurrency);

       String expectedRate = rateData.get(0).get("rate");
       String actualRate =  response.jsonPath().get("rate").toString();
       Assert.assertEquals(expectedRate, actualRate);


    }

    @Given("that a user make a Post request to set rate")
    public void thatAUserMakeAPostRequestToSetRate(List<Map<String, String>> rateData) {

        rateDataModel = rateDataModelBuilder
                .rate(Integer.parseInt(rateData.get(0).get("rate")))
                .fromCurrency(rateData.get(0).get("fromCurrency"))
                .toCurrency(rateData.get(0).get("toCurrency")).build();

        response =  rateCalcServices.createNewRate(rateDataModel);
    }

    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String successMsg) {
        Assert.assertEquals(response.jsonPath().get("message"), successMsg);
    }
}
