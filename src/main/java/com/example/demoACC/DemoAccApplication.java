package com.example.demoACC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoAccApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoAccApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/sharan")
	public String sharan(@RequestParam(value = "projStatus", defaultValue = "0 Percent") String projStatus) {
		return String.format("Project Status: %s", projStatus);
	}

}