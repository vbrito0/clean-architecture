package com.pratice.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@SpringBootApplication
@ComponentScan(basePackages = "com.pratice.java")
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
