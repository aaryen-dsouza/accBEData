package com.example.demoACC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.demoACC")
@RestController
public class DemoAccApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoAccApplication.class, args);
	}

}