package org.githubtrendinglanguages.languagetests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.http.MediaType;

public class LanguageControllerIntegrationTest extends BaseTest{
	@Test
	public void getTrendingLanguages() throws Exception {
	        mvc.perform(
	        		get("/api/languages")
	        			.contentType(MediaType.APPLICATION_JSON)
	        		)
	          		.andExpect(status().isOk())
	          		.andExpect(content()	
	          		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
