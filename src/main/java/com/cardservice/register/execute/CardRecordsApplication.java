package com.cardservice.register.execute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AutoConfiguration
@EntityScan("com.cardservice.register.dao")
@EnableJpaRepositories("com.cardservice.register.dao")
@ComponentScan(basePackages = {"com.cardservice.register"})
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
