package com.pratice.java;

import com.pratice.java.adapter.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.pratice.java")
@ConfigurationProperties
@Import(GlobalExceptionHandler.class)
public class Application implements CommandLineRunner {

	private static final Logger LOGGER_TECNICO = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String activeProfile = Arrays.toString(environment.getActiveProfiles());
		String profiles = defaultIfBlank(activeProfile.replace("[]", ""), "[LOCAL]");
		LOGGER_TECNICO.info("ACTIVE PROFILES: {}", profiles);
	}
}
