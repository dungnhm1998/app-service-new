package asia.leadsgen.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import asia.leadsgen.app.util.AppParams;
import asia.leadsgen.app.util.DBParams;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = DBParams.TB_THEME_TEMPLATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeTemplateEntity {

	@Id
	@Column(name = DBParams.S_ID)
	private String id;
	
	@Column(name = DBParams.S_SETTING_ID)
	@JsonProperty(value = AppParams.SETTING_ID)
	private String settingId;

	@Column(name = DBParams.S_NAME)
	private String name;

	@Column(name = DBParams.S_CONTENT)
	private String content;

	@Column(name = DBParams.S_TYPE)
	private String type;

	@Column(name = DBParams.S_STATE)
	private String state;

	@Column(name = DBParams.D_CREATE)
	@JsonProperty(value = AppParams.CREATE_AT)
	private Date createAt;

	@Column(name = DBParams.D_UPDATE)
	@JsonProperty(value = AppParams.UPDATE_AT)
	private Date updateAt;
}
