package org.iceo.apilayer.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsModule extends AbstractModule {

    private final static String CONFIG_FILE_PATH = "config.properties";

    @Provides
    @Singleton
    static Properties properties() throws IOException {
        Properties properties = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream(CONFIG_FILE_PATH);
        properties.load(input);

        return properties;
    }
}
