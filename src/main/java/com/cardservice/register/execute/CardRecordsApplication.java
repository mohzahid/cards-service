package com.cardservice.register.execute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cardservice.register.controller"})
public class CardRecordsApplication extends SpringBootServletInitializer {

	/*@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder applicationBuilder)
	{
		return applicationBuilder.sources( CardRecordsApplication.class );
	}*/
	public static void main(String[] args) {
		SpringApplication.run(CardRecordsApplication.class, args);
	}
}
