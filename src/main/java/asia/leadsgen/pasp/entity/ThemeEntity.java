package asia.leadsgen.pasp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "THEME")
public class ThemeEntity {

	@Id
	@Column(name = "S_ID")
	private String id;
	
	@Column(name = "S_NAME")
	@JsonProperty(value = "name")
	private String name;
	
	@Column(name = "S_USER_ID")
	@JsonProperty(value = "user_id")
	private String userId;
	
	@Column(name = "S_DOMAIN_ID")
	@JsonProperty(value = "domain_id")
	private String domainId;
	
	@Column(name = "S_STATE")
	private String state;
	
	@Column(name = "D_CREATE")
	@JsonProperty(value = "create_at")
	private Date createAt;
	
	@Column(name = "D_UPDATE")
	@JsonProperty(value = "update_at")
	private Date updateAt;
}
