package asia.leadsgen.pasp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObj {
	@JsonProperty(value = "server_code")
	private int serverCode;
	private String message;
}
