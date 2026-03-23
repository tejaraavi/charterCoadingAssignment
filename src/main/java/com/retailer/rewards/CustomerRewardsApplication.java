package com.retailer.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Customer Rewards Spring Boot application.
 * Enables JPA repositories and entity scanning.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.retailer.rewards.repository")
@EntityScan(basePackages = "com.retailer.rewards.model")
public class CustomerRewardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerRewardsApplication.class, args);
        System.out.println("Working fine");
    }
}