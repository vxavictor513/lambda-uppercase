package com.example.lambdauppercase;

import java.util.function.Function;

import reactor.core.publisher.Flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LambdaUppercaseApplication {

	@Bean
	public Function<Flux<String>, Flux<String>> uppercase() {
		return flux -> flux.map(String::toUpperCase);
	}

	public static void main(String[] args) {
		SpringApplication.run(LambdaUppercaseApplication.class, args);
	}

}
