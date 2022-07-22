package asia.leadsgen.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel<T> /* implements Serializable */ {
	private int code;
	private String message;
	private T data;
	private List<ErrorObj> error;
}
