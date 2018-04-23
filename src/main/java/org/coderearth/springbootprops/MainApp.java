package org.coderearth.springbootprops;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MainApp.class)
                .bannerMode(Banner.Mode.OFF)
                .registerShutdownHook(true)
                .build()
                .run(args)
                .close();
        LOGGER.info("MainApp shutting down !!");
    }

}
