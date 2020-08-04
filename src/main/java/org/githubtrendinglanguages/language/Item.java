package org.githubtrendinglanguages.language;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** igonre undefined propreties while parsing JSON to Item */
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityScan
@Data @AllArgsConstructor @NoArgsConstructor @Getter @Setter
/** Item class represents repositorie */
public class Item {
	private String name; /** repositorie name */
	private String html_url;
	private String language;
}

