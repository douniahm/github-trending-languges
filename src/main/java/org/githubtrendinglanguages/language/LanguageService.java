
package org.githubtrendinglanguages.language;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
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
	 * call Github's API and parse result to APIResult that contains a list of items(repositories)
	 * 
	 * @returns a list of items (Top 100 github repositories created in last 30 days).
	 */
	public HashMap<String, Language> callGithubAPI() {
	
			ResponseEntity<APIResult> result = restTemplate.exchange(this.GITHUB_API_URL, HttpMethod.GET, null,
					APIResult.class);
			return groupItemsByLanguage(result.getBody().getItems());
	}

	/**
	 * returns date of last 30 days
	 */
	private LocalDate getLastMonthDate() {
		LocalDate today = LocalDate.now();
		Period days_30 = Period.ofDays(30);
		return today.minus(days_30);
	}

	/**
	 * extracts languages and URLs of repositories from the list of repositories
	 * 
	 * @param repositories: top 100 repositories
	 * @return Hashmap of languages used in repositories
	 */
	private HashMap<String, Language> groupItemsByLanguage(ArrayList<Item> repositories) {
		HashMap<String, Language> languages = new HashMap<String, Language>();
		StringBuilder languageName = new StringBuilder();

		repositories.forEach(repositorie -> {
			repositorie.replaceNullLanguage();
			languageName.setLength(0);
			languageName.append(repositorie.getLanguage());

			/** case 1: language already added */
			if (languages.containsKey(languageName.toString())) {
				languages.get(languageName.toString()).addRepoUrl((repositorie.getHtml_url()));
			} else {
				/** case 2: language isn't added yet */
				Language language = new Language(languageName.toString(), repositorie.getHtml_url());
				languages.put(languageName.toString(), language);
			}
		});
		return sortLanguagesByNumberOfReposAsc(languages);
	}

	/**
	 * sort languages by number of repositories
	 * 
	 * @param HashMap of languages
	 * @return Hashmap of languages sorted
	 */
	private HashMap<String, Language> sortLanguagesByNumberOfReposAsc(HashMap<String, Language> languages) {
		return languages.entrySet().stream()
				.sorted((e1, e2) -> Integer.compare(e1.getValue().getNumberRepos(), e2.getValue().getNumberRepos()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}