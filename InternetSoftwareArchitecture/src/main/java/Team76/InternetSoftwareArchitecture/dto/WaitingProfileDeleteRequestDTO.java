package Team76.InternetSoftwareArchitecture.dto;

public class WaitingProfileDeleteRequestDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String reason;

	public WaitingProfileDeleteRequestDTO() {

	}

	public WaitingProfileDeleteRequestDTO(String firstName, String lastName, String email, String reason) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reason = reason;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
