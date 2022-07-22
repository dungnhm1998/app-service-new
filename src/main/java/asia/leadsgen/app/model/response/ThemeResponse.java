package asia.leadsgen.app.model.response;

import java.util.List;

import asia.leadsgen.app.entity.ThemeSettingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeResponse {

	private String id;
	private List<ThemeSettingEntity> themeSettingEntities;
}
