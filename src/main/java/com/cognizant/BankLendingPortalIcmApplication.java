package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cognizant.entities")
@EnableJpaRepositories(basePackages = "com.cognizant.repository")
//@ComponentScan(basePackages = {"com.cognizant.*","com.cognizant.service","com.cognizant.repository"})
@SpringBootApplication(scanBasePackages = "com.cognizant.*")
@ComponentScan(basePackages = "com.cognizant.*")
public class BankLendingPortalIcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankLendingPortalIcmApplication.class, args);

	}

}
		