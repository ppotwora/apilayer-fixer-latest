package org.iceo.apilayer.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.ScenarioScope;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomObjectFactory implements ObjectFactory {

    Injector injector;

    public CustomObjectFactory() {
        this.injector = Guice.createInjector(
                Stage.PRODUCTION,
                CucumberModules.createScenarioModule(),
                new HttpModule(),
                new PropsModule());
    }

    @Override
    public void start() {
        this.injector.getInstance(ScenarioScope.class).enterScope();
    }

    @Override
    public void stop() {
        this.injector.getInstance(ScenarioScope.class).exitScope();
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> clazz) {
        return this.injector.getInstance(clazz);
    }
}
