package org.iceo.apilayer.helpers;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.guice.ScenarioScoped;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.*;
import org.iceo.apilayer.utils.ScenarioContext;

import java.io.IOException;
import java.util.Properties;

@ScenarioScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GeneralHelper {

    OkHttpClient httpClient;
    ScenarioContext scenarioContext;
    ObjectMapper objectMapper;
    Properties properties;

    @Inject
    public GeneralHelper(OkHttpClient httpClient, ScenarioContext scenarioContext, ObjectMapper objectMapper,
                         Properties properties) {
        this.httpClient = httpClient;
        this.scenarioContext = scenarioContext;
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public String getApiKey() {
        return properties.getProperty("API_KEY");
    }

    public Request callGet(String endpoint, String apiKey) {
        return new Request.Builder()
                .url(getBaseUrl() + endpoint)
                .addHeader("apikey", apiKey)
                .get()
                .build();
    }

    public Request callGetWithoutAnyTokens(String endpoint) {
        return new Request.Builder()
                .url(getBaseUrl() + endpoint)
                .get()
                .build();
    }

    public Request callGetWithParameters(String endpoint, String base, String symbols) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(getBaseUrl() + endpoint).newBuilder();

        if (base != null && !base.isEmpty()) {
            urlBuilder.addQueryParameter("base", base);
        }

        if (symbols != null && !symbols.isEmpty()) {
            urlBuilder.addQueryParameter("symbols", symbols);
        }

        return new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("apikey", getApiKey())
                .get()
                .build();
    }

    public <T> T sendRequestAndParseResponse(Request request, Class<T> responseClass) throws IOException {
        try (Response response = httpClient.newCall(request).execute()) {
            scenarioContext.addResponseCode(response.code());

            if (response.isSuccessful()) {
                try (ResponseBody responseBody = response.body()) {
                    if (responseBody != null) {
                        String responseBodyString = responseBody.string();
                        return objectMapper.readValue(responseBodyString, responseClass);
                    } else {
                        throw new IOException("Response body is null");
                    }
                }
            } else {
                try (ResponseBody responseBody = response.body()) {
                    if (responseBody != null) {
                        String responseBodyString = responseBody.string();
                        return objectMapper.readValue(responseBodyString, responseClass);
                    } else {
                        throw new IOException("Response body is null");
                    }
                }
            }
        }
    }
}
