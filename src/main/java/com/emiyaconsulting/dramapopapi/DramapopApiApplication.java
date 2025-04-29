package com.emiyaconsulting.dramapopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DramapopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DramapopApiApplication.class, args);
    }

}
