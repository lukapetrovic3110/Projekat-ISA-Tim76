package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class CottageOwner extends User {
	
	private static final long serialVersionUID = 5797369556253038949L;
	
	@Column(name = "rating")
	private Double rating;
	
	@Column(name = "explanation")
	private String explanation;
	
	@Enumerated(EnumType.STRING)
	private AccountApproval accountApproval;

	public CottageOwner() { }

	public CottageOwner(Double rating, String explanation, AccountApproval accountApproval) {
		super();
		this.rating = rating;
		this.explanation = explanation;
		this.accountApproval = accountApproval;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
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
