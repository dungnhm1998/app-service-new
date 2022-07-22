package asia.leadsgen.app.model.exception;

import asia.leadsgen.app.error.SystemError;
import asia.leadsgen.app.util.AppParams;
import asia.leadsgen.app.util.ParamUtil;

import java.util.Map;

/**
 * Created by HungDX on 22-Jan-16.
 */
public class HttpServiceException extends SystemException {

	public HttpServiceException(int code, String reason, Map informationMap) {

		super(new SystemError(code, reason, ParamUtil.getString(informationMap, AppParams.DETAILS)));
	}
}
