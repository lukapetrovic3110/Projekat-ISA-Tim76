package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ShipOwner extends User {

	private static final long serialVersionUID = 9163089365319355578L;
	
	@Column(name = "explanation")
	private String explanation;

	@Enumerated(EnumType.STRING)
	private AccountApproval accountApproval;
	
	public ShipOwner() { }
	
	public ShipOwner(String explanation, AccountApproval accountApproval) {
		super();
		this.explanation = explanation;
		this.accountApproval = accountApproval;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public AccountApproval getAccountApproval() {
		return accountApproval;
	}

	public void setAccountApproval(AccountApproval accountApproval) {
		this.accountApproval = accountApproval;
	}

}
