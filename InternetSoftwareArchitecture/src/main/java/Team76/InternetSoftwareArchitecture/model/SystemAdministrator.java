package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SystemAdministrator extends User {

	private static final long serialVersionUID = 9124019828806257069L;

	@Column(name = "firstLoginChangePassword", nullable = false)
	private Boolean firstLoginChangePassword;

	public SystemAdministrator(Boolean firstLoginChangePassword) {
		super();
		this.firstLoginChangePassword = firstLoginChangePassword;
	}

	public Boolean getFirstLoginChangePassword() {
		return firstLoginChangePassword;
	}

	public void setFirstLoginChangePassword(Boolean firstLoginChangePassword) {
		this.firstLoginChangePassword = firstLoginChangePassword;
	}

}
