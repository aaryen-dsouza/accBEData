package com.example.demoACC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.demoACC")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoAccApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoAccApplication.class, args);
	}

}