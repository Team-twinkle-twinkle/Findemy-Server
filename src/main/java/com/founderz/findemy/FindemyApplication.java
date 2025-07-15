package com.founderz.findemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@ConfigurationPropertiesScan
@SpringBootApplication
public class FindemyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindemyApplication.class, args);
    }

}
