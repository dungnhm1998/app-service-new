package asia.leadsgen.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import asia.leadsgen.app.util.AppParams;
import asia.leadsgen.app.util.DBParams;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = DBParams.TB_THEME)
public class ThemeEntity {

	@Id
	@Column(name = DBParams.S_ID)
	private String id;
	
	@Column(name = DBParams.S_NAME)
	@JsonProperty(value = AppParams.NAME)
	private String name;
	
	@Column(name = DBParams.S_USER_ID)
	@JsonProperty(value = AppParams.USER_ID)
	private String userId;
	
	@Column(name = DBParams.S_DOMAIN_ID)
	@JsonProperty(value = AppParams.DOMAIN_ID)
	private String domainId;
	
	@Column(name = DBParams.S_STATE)
	private String state;
	
	@Column(name = DBParams.D_CREATE)
	@JsonProperty(value = AppParams.CREATE_AT)
	private Date createAt;
	
	@Column(name = DBParams.D_UPDATE)
	@JsonProperty(value = AppParams.UPDATE_AT)
	private Date updateAt;
}
