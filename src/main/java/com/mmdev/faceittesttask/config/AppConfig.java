package com.mmdev.faceittesttask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up application-wide beans.
 */
@Configuration
public class AppConfig {

	/**
	 * Creates and returns a RestTemplate bean.
	 * <p>
	 * The RestTemplate is used for making HTTP requests to external APIs.
	 * This bean is automatically managed by Spring and can be injected
	 * into other components where needed.
	 * </p>
	 *
	 * @return A RestTemplate instance.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
