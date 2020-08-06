package org.githubtrendinglanguages.languagetests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import org.githubtrendinglanguages.language.Language;
import org.githubtrendinglanguages.language.LanguageService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class LanguageControllerUnitTest extends BaseTest {
	@MockBean
	protected LanguageService languageService;

	@Test
	public void getTrendingLanguages() throws Exception {
		/** mock API response */
		given(this.languageService.callGithubAPI()).willReturn(getTestData());
		
		/** send HTTP GET request to the controller */
		mvc.perform(get("/api/languages"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(2)))
			.andExpect(jsonPath("$.Java.name", is("Java")))
			.andExpect(jsonPath("$.Java.numberRepos", is(1)))
			.andExpect(jsonPath("$.Java.reposUrls.[0]", is("url1")))
			.andExpect(jsonPath("$.C#.name", is("C#")))
			.andExpect(jsonPath("$.C#.numberRepos", is(1)))
			.andExpect(jsonPath("$.C#.reposUrls.[0]", is("url2")));
	}

	private HashMap<String, Language> getTestData() {
		return new HashMap<String, Language>() {
			{
				put("Java", new Language("Java", "url1"));
				put("C#", new Language("C#", "url2"));
			}
		};
	}
}
