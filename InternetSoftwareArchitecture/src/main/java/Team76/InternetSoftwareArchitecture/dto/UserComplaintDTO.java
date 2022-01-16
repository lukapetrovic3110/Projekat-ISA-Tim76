package Team76.InternetSoftwareArchitecture.dto;

public class UserComplaintDTO {
	
	private Long complaintId;
	private String accusedFirstName;
	private String accusedLastName;
	private String accusedEmail;
	private String comment;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	
	public UserComplaintDTO() {
		
	}
	
	public UserComplaintDTO(Long complaintId, String accusedFirstName, String accusedLastName, String accusedEmail,
			String comment, String clientFirstName, String clientLastName, String clientEmail) {
		super();
		this.complaintId = complaintId;
		this.accusedFirstName = accusedFirstName;
		this.accusedLastName = accusedLastName;
		this.accusedEmail = accusedEmail;
		this.comment = comment;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.clientEmail = clientEmail;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public String getAccusedFirstName() {
		return accusedFirstName;
	}

	public void setAccusedFirstName(String accusedFirstName) {
		this.accusedFirstName = accusedFirstName;
	}

	public String getAccusedLastName() {
		return accusedLastName;
	}

	public void setAccusedLastName(String accusedLastName) {
		this.accusedLastName = accusedLastName;
	}

	public String getAccusedEmail() {
		return accusedEmail;
	}

	public void setAccusedEmail(String accusedEmail) {
		this.accusedEmail = accusedEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
}
