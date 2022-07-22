package asia.leadsgen.app.model.exception;


import asia.leadsgen.app.error.SystemError;

/**
 * Created by DungNHM on 05-07-2022.
 *
 * What can you do
 */
public class AuthorizationException extends SystemException {

	public AuthorizationException(SystemError error) {
		super(error);
	}
}
