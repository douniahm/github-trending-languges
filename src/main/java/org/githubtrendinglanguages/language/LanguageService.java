package org.githubtrendinglanguages.language;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LanguageService {
	private final String GITHUB_API_URL;
	@Autowired
	private RestTemplate restTemplate;

	public LanguageService() {
		this.GITHUB_API_URL = "https://api.github.com/search/repositories?q=created:>" 
				+ this.getLastMonthDate()
				+ "&sort=stars&order=descpage=1&per_page=100";
	}

	/**
	 * @param rest template builder
	 * @return rest template instance
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * returns a list of items (Top 100 github repositories created in last 30
	 * days).
	 */
	public List<Item> callGithubAPI() {
		/**
		 * call Github's API and parse result to APIResult that contains a list of
		 * items( repositories)
		 */
		ResponseEntity<APIResult> result = restTemplate.exchange(this.GITHUB_API_URL, HttpMethod.GET, null,
				APIResult.class);
		return result.getBody().getItems();
	}

	/**
	 * returns date of last 30 days
	 */
	private LocalDate getLastMonthDate() {
		LocalDate today = LocalDate.now();
		Period days_30 = Period.ofDays(30);
		return today.minus(days_30);
	}
}
