package asia.leadsgen.pasp.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {

	private String type;
	private String id;
	private List<ChildrenModel> children;
}
