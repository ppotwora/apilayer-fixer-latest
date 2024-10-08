package org.iceo.apilayer.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.OkHttpClient;

public class HttpModule extends AbstractModule {

    @Provides
    @Singleton
    static ObjectMapper objectMapper() {
        return new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    @Provides
    static OkHttpClient okHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .build();
    }
}
