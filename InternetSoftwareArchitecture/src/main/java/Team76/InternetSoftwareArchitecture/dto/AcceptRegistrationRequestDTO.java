package Team76.InternetSoftwareArchitecture.dto;

public class AcceptRegistrationRequestDTO {

	private String requestEmail;

	public AcceptRegistrationRequestDTO() {

	}

	public AcceptRegistrationRequestDTO(String requestEmail) {
		super();
		this.requestEmail = requestEmail;
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

}
