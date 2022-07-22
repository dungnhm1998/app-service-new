package asia.leadsgen.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import asia.leadsgen.app.util.AppParams;
import asia.leadsgen.app.util.DBParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = DBParams.TB_THEME_SETTING)
public class ThemeSettingEntity {

	@Id
	@Column(name = DBParams.S_ID)
	private String id;

	@Column(name = DBParams.S_THEME_ID)
	@JsonProperty(value = AppParams.THEME_ID)
	private String themeId;

	@Column(name = DBParams.S_VALUE)
	private String value;

	@Column(name = DBParams.N_POSITION)
	private int position;

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
	
	@Column(name = DBParams.S_TEMPLATE_ID)
	@JsonProperty(value = AppParams.TEMPLATE_ID)
	private String templateId;
	
	@Transient
	@JsonProperty(value = AppParams.COMPONENT_ID)
	private String componentId;
}