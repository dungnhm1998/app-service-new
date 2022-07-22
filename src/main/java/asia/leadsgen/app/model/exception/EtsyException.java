package asia.leadsgen.app.model.exception;

import asia.leadsgen.app.error.SystemError;

public class EtsyException extends SystemException {
	public EtsyException(SystemError error) {
		super(error);
	}
}
