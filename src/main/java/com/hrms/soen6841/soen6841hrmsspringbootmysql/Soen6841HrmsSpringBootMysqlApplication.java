package com.hrms.soen6841.soen6841hrmsspringbootmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Soen6841HrmsSpringBootMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Soen6841HrmsSpringBootMysqlApplication.class, args);
	}
}
