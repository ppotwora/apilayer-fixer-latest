package org.iceo.apilayer.steps;

import com.google.inject.Inject;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.iceo.apilayer.helpers.GeneralHelper;
import org.iceo.apilayer.http.models.requests.GetLatestInput;
import org.iceo.apilayer.http.models.responses.GetErrorResponse;
import org.iceo.apilayer.http.models.responses.GetLatestOutput;
import org.iceo.apilayer.http.models.responses.NoTokenErrorResponse;
import org.iceo.apilayer.utils.ScenarioContext;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@ScenarioScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GetLatestSteps {

    ObjectMapper objectMapper;
    GeneralHelper generalHelper;
    ScenarioContext scenarioContext;

    @Inject
    public GetLatestSteps(ObjectMapper objectMapper, GeneralHelper generalHelper, ScenarioContext scenarioContext) {
        this.objectMapper = objectMapper;
        this.generalHelper = generalHelper;
        this.scenarioContext = scenarioContext;
    }

    @DataTableType
    public GetLatestInput getLatestInput(Map<String, String> inputModel) {
        return objectMapper.convertValue(inputModel, GetLatestInput.class);
    }

    @Given("^I have a valid API key as a user$")
    public void iHaveAValidApiKeyAsUser() {
        assertThat(generalHelper.getApiKey()).isNotBlank();
    }

    @When("^I call GET_latest endpoint: (.*)$")
    /***
     * put your code here
     */

    @When("^I call GET_latest endpoint (.*) with query \\(nulls ignored\\):$")
    public void iCallGetLatestEndpointWithQuery(String endpoint, GetLatestInput getLatestInput) throws IOException {
        callGetLatestEndpoint(endpoint, getLatestInput, GetLatestOutput.class);
    }

    @When("^I call GET_latest endpoint (.*) with query with non existent base param \\(nulls ignored\\):$")
    public void iCallGetLatestEndpointWithQueryWithNonExistBase(String endpoint, GetLatestInput getLatestInput) throws IOException {
        callGetLatestEndpoint(endpoint, getLatestInput, GetErrorResponse.class);
    }

    @When("^I call GET_latest endpoint (.*) with no access token in header$")
    /***
     * put your code here
     */

    @Then("^I receive (.*) code$")
    /***
     * put your code here
     */

    @Then("^default schema response body matches model from documentation$")
    /***
     * put your code here
     */

    @Then("^no token error schema response body matches model from documentation$")
    /***
     * put your code here
     */

    @Then("^error schema response body matches model from documentation$")
    public void errorSchemaResponseBodyMatchesModelFromDocumentation() throws IOException {
        val response = objectMapper.writeValueAsString(scenarioContext.getResponse());
        compareActualToExpectedData(response, GetErrorResponse.class);
    }

    private <T> void callGetLatestEndpoint(String endpoint, GetLatestInput input, Class<T> responseClass)
            throws IOException {
        val request = generalHelper.callGetWithParameters(endpoint, input.getBase(), input.getSymbols());
        val response = generalHelper.sendRequestAndParseResponse(request, responseClass);

        scenarioContext.addResponse(response);
    }

    private <T> void compareActualToExpectedData(String actualResponseBody, Class<T> expectedClass) {
        try {
            T expectedData = objectMapper.readValue(actualResponseBody, expectedClass);
            assertNotNull("Parsed response should not be null", expectedData);

            if (expectedData instanceof GetLatestOutput output) {
                assertThat(output.getBase()).isNotBlank();
                assertThat(output.getDate()).isNotBlank();
                assertThat(output.getRates()).isNotEmpty();
                assertThat(output.getSuccess()).isNotNull();
                assertThat(output.getTimestamp()).isNotNull();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing response body: " + e.getMessage(), e);
        }
    }
}
