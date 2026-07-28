//package com.emiyaconsulting.dramapopapi.utilities;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Component
//public class TimeZoneSanityCheck implements CommandLineRunner {
//    private final DataSource dataSource;
//    
//    @Autowired
//    public TimeZoneSanityCheck(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    
//    @Override
//    public void run(String... args) throws Exception {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT NOW(), UTC_TIMESTAMP()");
//             ResultSet rs = ps.executeQuery()) {
//            rs.next();
//            LocalDateTime mysqlNowldt = rs.getObject(1, LocalDateTime.class);
//            LocalDateTime mysqlUTCldt = rs.getObject(2, LocalDateTime.class);
//            
//            Instant mysqlNow = mysqlNowldt.toInstant(ZoneOffset.UTC);
//            Instant mysqlUTC = mysqlUTCldt.toInstant(ZoneOffset.UTC);
//            
//            System.out.println("MYSQL NOW(): " + mysqlNow);
//            System.out.println("MYSQL UTC_TIMESTAMP(): " + mysqlUTC);
//            System.out.println("Java Instant.now(): " + java.time.Instant.now());
//        }
//    }
//}
