package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jobportal", "com.jobportal.dao"})
public class SearchJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchJobApplication.class, args);
    }
}
