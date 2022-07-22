package asia.leadsgen.app.controller.theme;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asia.leadsgen.app.model.ResultModel;
import asia.leadsgen.app.model.request.ThemeRequest;
import asia.leadsgen.app.service.ThemeSettingService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/themes")
public class ThemeController {

	@Autowired
	ThemeSettingService themeSettingService;

	@PutMapping("/update")
//	@Operation(summary = "List Products")
	public ResponseEntity<ResultModel> update(@RequestBody ThemeRequest request) throws SQLException {

		ResultModel resultObject = themeSettingService.update(request);

		return new ResponseEntity<>(resultObject, HttpStatus.OK);
	}

}
