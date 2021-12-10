package Team76.InternetSoftwareArchitecture.dto;

public class UserDeleteAccountRequestDTO {

	private String reason;

	public UserDeleteAccountRequestDTO() {

	}

	public UserDeleteAccountRequestDTO(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
