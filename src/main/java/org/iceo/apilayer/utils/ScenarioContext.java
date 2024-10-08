package org.iceo.apilayer.utils;

import io.cucumber.guice.ScenarioScoped;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@ScenarioScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScenarioContext {

    Map<String, Object> scenarioContext = new HashMap<>();

    /***
     * Adds a key-value pair to ScenarioContext
     * @param key Context Enum under which the value will be stored
     * @param value The value to be added to the context
     * @return Itself
     */
    public ScenarioContext setContext(Context key, Object value) {
        return setContext(key.toString(), value);
    }

    /***
     * Adds a key-value pair to ScenarioContext
     * @param key Key under which the value will be stored
     * @param value The value to be added to the context
     * @return Itself
     */
    public ScenarioContext setContext(String key, Object value) {
        scenarioContext.put(key, value);
        return this;
    }

    /***
     * Retrieves a value from ScenarioContext by its key
     * @param key Context Enum representing the key for the context data
     * @return The value stored under the provided key, or null if not found
     */
    public <T> T getContext(Context key) {
        return (T) scenarioContext.get(key.toString());
    }

    /***
     * Adds an HTTP response to the context under the HTTP_RESPONSE key
     * @param response The response object to add
     * @return Itself
     */
    public ScenarioContext addResponse(Object response) {
        setContext(Context.HTTP_RESPONSE, response);
        return this;
    }

    /***
     * Retrieves the HTTP response stored in the context
     * @return The stored HTTP response, or null if not present
     */
    public <T> T getResponse() {
        return getContext(Context.HTTP_RESPONSE);
    }

    /***
     * Adds an HTTP response code to the context under the HTTP_RESPONSE_CODE key
     * @param responseCode The http response code to store
     * @return Itself
     */
    public ScenarioContext addResponseCode(int responseCode) {
        setContext(Context.HTTP_RESPONSE_CODE, responseCode);
        return this;
    }

    /***
     * Retrieves the HTTP response code stored in the context
     * @return The stored HTTP response code, or null if not present
     */
    public int getResponseCode() {
        return getContext(Context.HTTP_RESPONSE_CODE);
    }
}



