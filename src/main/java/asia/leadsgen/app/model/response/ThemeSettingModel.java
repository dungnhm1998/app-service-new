package asia.leadsgen.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeSettingModel {

	private String id;
	@JsonProperty(value = "background_color")
	private String backgroundColor;
	@JsonProperty(value = "text_color")
	private String textColor;
	private int position;
}
