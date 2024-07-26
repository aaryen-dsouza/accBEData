package com.example.demoACC;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoAccApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoAccApplication.class, args);
	}

}