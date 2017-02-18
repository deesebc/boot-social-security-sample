package com.bss.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = { "com.bss.sample.app" } )
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure( final SpringApplicationBuilder application ) {
		return application.sources( Application.class );
	}

	public static void main( final String[] args ) throws Throwable {
		SpringApplication.run( Application.class, args );

	}
}
