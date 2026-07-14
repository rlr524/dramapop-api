package com.emiyaconsulting.dramapopapi;

import com.emiyaconsulting.dramapopapi.utilities.TimeZoneSanityCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableJpaAuditing
public class DramapopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DramapopApiApplication.class, args);
    }
}
