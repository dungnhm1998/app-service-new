package asia.leadsgen.pasp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueModel {

	private String id;
	private String type;
	private String value;
}
