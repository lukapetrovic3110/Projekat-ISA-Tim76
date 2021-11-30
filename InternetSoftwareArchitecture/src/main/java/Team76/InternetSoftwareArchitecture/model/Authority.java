package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "authorityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorityId;

	@Column(name = "name")
	private String name;
	
	public Authority() {
		super();
	}
	
	public Authority(String name) {
		this.name = name;
	}

	public Authority(Long authorityId, String name) {
		super();
		this.authorityId = authorityId;
		this.name = name;
	}


	@Override
	public String getAuthority() {
		return name;
	}

	
	public Long getAuthorityId() {
		return authorityId;
	}


	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
