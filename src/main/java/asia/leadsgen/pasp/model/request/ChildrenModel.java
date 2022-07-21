package asia.leadsgen.pasp.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenModel {

	private String heading;
	private String id;
	private String type;
	private List<ValueModel> data;
}
