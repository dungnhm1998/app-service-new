package asia.leadsgen.pasp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "THEME_SETTING")
public class ThemeSettingEntity {

	@Id
	@Column(name = "S_ID")
	private String id;

	@Column(name = "S_THEME_ID")
	@JsonProperty(value = "theme_id")
	private String themeId;

	@Column(name = "S_VALUE")
	private String value;

	@Column(name = "N_POSITION")
	private int position;

	@Column(name = "S_TYPE")
	private String type;

	@Column(name = "S_STATE")
	private String state;

	@Column(name = "D_CREATE")
	@JsonProperty(value = "create_at")
	private Date createAt;

	@Column(name = "D_UPDATE")
	@JsonProperty(value = "update_at")
	private Date updateAt;
	
	@Column(name = "S_TEMPLATE_ID")
	@JsonProperty(value = "template_id")
	private String templateId;
	
	@Transient
	@JsonProperty(value = "component_id")
	private String componentId;
}