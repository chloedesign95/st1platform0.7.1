package com.st1.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class St1PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(St1PlatformApplication.class, args);
    }

}
