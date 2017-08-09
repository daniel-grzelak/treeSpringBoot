package com.daniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class TreeApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(TreeApplication.class, args);
	}
}
