package com.example.lambdauppercase;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class LambdaUppercaseApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	@Bean
	public Function<Foo, Bar> uppercase() {
		return v -> new Bar("Hello " + v.getMessage().toUpperCase());
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("uppercase", FunctionRegistration.class,
				() -> new FunctionRegistration<>(uppercase())
						.type(FunctionType.from(Foo.class).to(Bar.class)));
	}

	public static void main(String[] args) {
		SpringApplication.run(LambdaUppercaseApplication.class, args);
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Foo {
	private String message;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Bar {
	private String value;
}