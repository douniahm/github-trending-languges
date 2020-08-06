package org.githubtrendinglanguages;

import org.githubtrendinglanguages.language.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubTrendingLanguagesApplication {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(GithubTrendingLanguagesApplication.class, args);
	}

}
