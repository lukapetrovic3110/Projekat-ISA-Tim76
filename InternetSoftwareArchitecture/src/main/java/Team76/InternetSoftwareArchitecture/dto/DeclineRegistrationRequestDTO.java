package Team76.InternetSoftwareArchitecture.dto;

public class DeclineRegistrationRequestDTO {

	private String requestEmail;
	private String comment;

	public DeclineRegistrationRequestDTO() {

	}

	public DeclineRegistrationRequestDTO(String requestEmail, String comment) {
		super();
		this.requestEmail = requestEmail;
		this.comment = comment;
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
