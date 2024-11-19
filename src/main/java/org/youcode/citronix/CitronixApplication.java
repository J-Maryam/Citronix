package org.youcode.citronix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitronixApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitronixApplication.class, args);
		System.out.println("Citronix Application Started");
	}

}
