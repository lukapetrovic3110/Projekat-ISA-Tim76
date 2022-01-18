package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class FishingInstructor extends User {

	private static final long serialVersionUID = -7744732584895885510L;

	@Column(name = "rating")
	private Double rating;
	
	@Column(name = "explanation")
	private String explanation;

	@Enumerated(EnumType.STRING)
	private AccountApproval accountApproval;
	
	public FishingInstructor() { }

	public FishingInstructor(Double rating, String explanation, AccountApproval accountApproval) {
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
