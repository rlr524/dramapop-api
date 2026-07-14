package com.emiyaconsulting.dramapopapi.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class TimeZoneSanityCheck implements CommandLineRunner {
    private final DataSource dataSource;
    
    @Autowired
    public TimeZoneSanityCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT NOW(), UTC_TIMESTAMP()");
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            System.out.println("MYSQL NOW(): " + rs.getTimestamp(1));
            System.out.println("MYSQL UTC_TIMESTAMP(): " + rs.getTimestamp(2) );
            System.out.println("Java Instant.now(): " + java.time.Instant.now());
        }
    }
}
