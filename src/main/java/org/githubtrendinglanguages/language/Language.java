package org.githubtrendinglanguages.language;

import java.util.LinkedList;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityScan
@Data @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Language {
	private String name;
	private int numberRepos;
	private LinkedList<String> reposUrls;
}
