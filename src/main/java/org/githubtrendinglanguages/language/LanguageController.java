package org.githubtrendinglanguages.language;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageController {
	@Autowired
	LanguageService languageService;

	@RequestMapping(value = "languages", method = RequestMethod.GET)
	public Map<String, Language> getTopLanguages() {
		return languageService.callGithubAPI();
	}
}