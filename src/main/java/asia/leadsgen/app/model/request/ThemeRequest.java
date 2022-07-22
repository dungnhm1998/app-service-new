package asia.leadsgen.app.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeRequest {

	@JsonProperty(value = "theme_id")
	private String themeId;
	private String type;
	@JsonProperty(value = "template_id")
	private String templateId;
	private List<SettingModel> settings;

}
