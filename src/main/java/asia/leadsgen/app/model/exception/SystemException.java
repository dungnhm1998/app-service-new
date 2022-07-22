package asia.leadsgen.app.model.exception;


import asia.leadsgen.app.error.SystemError;
import lombok.Data;

@Data
public class SystemException extends RuntimeException {

	private SystemError systemError;

	public SystemError getSystemError() {
		return systemError;
	}

	public SystemException(SystemError error) {
		super(error.getMessage());
		this.systemError = error;
	}
}
