package asia.leadsgen.pasp.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentModel {

	private String heading;
	private String id;
	private String type;
	List<PropertyModel> properties;
}
