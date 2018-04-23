package org.coderearth.springbootprops;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CmdAppRunner implements CommandLineRunner {

    @Value("${app.full.refresh}")
    private boolean refresh;

    private final LocalDate fallbackSince;

    public CmdAppRunner(@Value("${app.fallback.since}") final String fallbackSince) {
        this.fallbackSince = LocalDate.parse(fallbackSince, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public void run(final String... args) {
        LOGGER.debug("Starting up CmdAppRunner !!");
        LOGGER.info("app.full.refresh ==> {}", refresh);
        LOGGER.info("app.fallback.since ==> {}", fallbackSince);
        LOGGER.debug("Shutting down CmdAppRunner !!");
    }
}
