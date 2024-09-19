package com.fedoraa.presencebackend.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConnection {
    @Bean
    public Connection getConnection()throws SQLException {
        return DriverManager.getConnection(
                PostgresqlConfig.url,
                PostgresqlConfig.user,
                PostgresqlConfig.password
        );
    }
}