package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;

public class WaitingRegistrationRequestDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private AccountApproval accountApproval;
	private String explanation;
	
	public WaitingRegistrationRequestDTO() {
	
	}

	public WaitingRegistrationRequestDTO(String firstName, String lastName, String email, String phoneNumber,
			AccountApproval accountApproval, String explanation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.accountApproval = accountApproval;
		this.explanation = explanation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AccountApproval getAccountApproval() {
		return accountApproval;
	}

	public void setAccountApproval(AccountApproval accountApproval) {
		this.accountApproval = accountApproval;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


}
