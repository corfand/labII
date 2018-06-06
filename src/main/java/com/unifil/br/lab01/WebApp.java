package com.unifil.br.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EntityScan("com.unifil.br.entity")
@EnableJpaRepositories("com.unifil.br.repository")
@EnableAutoConfiguration
@ComponentScan("com.unifil.br.resource")
public class WebApp {
	//@GetMapping("/")
	@RequestMapping(value="/oi", method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}

}
