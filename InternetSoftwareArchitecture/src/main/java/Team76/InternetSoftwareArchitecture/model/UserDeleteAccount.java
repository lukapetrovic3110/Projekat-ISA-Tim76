package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserDeleteAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userDeleteAccountId", unique = true, nullable = false)
	private Long userDeleteAccountId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;
	
	@Column(name = "reason", nullable = false)
	private String reason; 
	
	public UserDeleteAccount() {
		
	}

	public UserDeleteAccount(Long userDeleteAccountId, User user, String reason) {
		this.userDeleteAccountId = userDeleteAccountId;
		this.user = user;
		this.reason = reason;
	}

	public Long getUserDeleteAccountId() {
		return userDeleteAccountId;
	}

	public void setUserDeleteAccountId(Long userDeleteAccountId) {
		this.userDeleteAccountId = userDeleteAccountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
