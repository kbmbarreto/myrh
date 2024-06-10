package br.com.lambdateam.myrh;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyrhApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrhApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC -3:00"));
	}
}