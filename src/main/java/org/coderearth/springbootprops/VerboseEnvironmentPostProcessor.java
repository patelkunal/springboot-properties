package org.coderearth.springbootprops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;


@Component
public class VerboseEnvironmentPostProcessor implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent> {

    private static final DeferredLog LOGGER = new DeferredLog();

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {
        LOGGER.info(environment.getPropertySources().size());
        environment.getPropertySources().forEach(x -> LOGGER.info(x));
        for (final PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource instanceof CommandLinePropertySource || propertySource instanceof OriginTrackedMapPropertySource) {
                dumpProp(propertySource, "app.full.refresh");
                dumpProp(propertySource, "app.fallback.since");
            }
        }
    }

    private void dumpProp(final PropertySource propertySource, String propertyName) {
        if (propertySource.containsProperty(propertyName)) {
            LOGGER.debug(String.format("%s is resolved from %s ==> %s", propertyName, propertySource.getName(), propertySource.getProperty(propertyName)));
        } else {
            LOGGER.debug(String.format("%s is not present in %s", propertyName, propertySource.getName()));
        }
    }

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        LOGGER.replayTo(VerboseEnvironmentPostProcessor.class);
    }
}
