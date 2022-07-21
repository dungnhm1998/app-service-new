package asia.leadsgen.pasp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THEME_TEMPLATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeTemplateEntity {

	@Id
	@Column(name = "S_ID")
	private String id;
	
	@Column(name = "S_SETTING_ID")
	@JsonProperty(value = "setting_id")
	private String settingId;

	@Column(name = "S_NAME")
	private String name;

	@Column(name = "S_CONTENT")
	private String content;

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
}
