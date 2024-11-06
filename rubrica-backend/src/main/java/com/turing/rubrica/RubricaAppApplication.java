package com.turing.rubrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RubricaAppApplication {

	public static void main(String[] args) {
            SpringApplication.run(RubricaAppApplication.class, args);
	}

}
