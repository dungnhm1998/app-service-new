package asia.leadsgen.app.model.exception;


import asia.leadsgen.app.error.SystemError;

/**
 * Created by DungNHM on 05-07-2022.
 *
 * Who are you
 */
public class AuthenticationException extends SystemException {

	public AuthenticationException(SystemError error) {
		super(error);
	}
}
