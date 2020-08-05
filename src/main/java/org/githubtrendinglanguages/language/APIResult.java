package org.githubtrendinglanguages.language;

import java.util.ArrayList;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*** igonre undefined propreties while parsing json to APIResult**/
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityScan
@Data @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class APIResult {
	private ArrayList<Item> items;
}