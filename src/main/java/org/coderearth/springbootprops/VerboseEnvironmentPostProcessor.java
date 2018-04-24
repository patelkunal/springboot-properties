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

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
public class VerboseEnvironmentPostProcessor implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent> {

    private static final DeferredLog LOGGER = new DeferredLog();

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {
        LOGGER.trace("Number of PropertySources ==> " + environment.getPropertySources().size());

        LOGGER.debug("Precedence of PropertySources --> " +
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(environment.getPropertySources().iterator(), Spliterator.ORDERED), false)
                        .map(PropertySource::getName)
                        .collect(Collectors.joining(" ==> "))
        );

        StreamSupport.stream(Spliterators.spliteratorUnknownSize(environment.getPropertySources().iterator(), Spliterator.ORDERED), false)
                .filter(ps -> ps instanceof CommandLinePropertySource || ps instanceof OriginTrackedMapPropertySource)
                .forEach(ps -> {
                    LOGGER.debug(String.format("%s(%s) ==> %s", ps.getName(), "app.full.refresh", ps.containsProperty("app.full.refresh") ? ps.getProperty("app.full.refresh") : "NOT_PRESENT"));
                    LOGGER.debug(String.format("%s(%s) ==> %s", ps.getName(), "app.fallback.since", ps.containsProperty("app.fallback.since") ? ps.getProperty("app.fallback.since") : "NOT_PRESENT"));
                });
    }

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        LOGGER.replayTo(VerboseEnvironmentPostProcessor.class);
    }
}
