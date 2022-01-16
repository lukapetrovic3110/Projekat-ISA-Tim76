package Team76.InternetSoftwareArchitecture.dto;

public class AnswerComplaintRequestDTO {
	
	private Long complaintRequestId;
	private String clientEmail;
	private String userEmail;
	private String comment;
	
	public AnswerComplaintRequestDTO() {
		
	}

	public Long getComplaintRequestId() {
		return complaintRequestId;
	}

	public void setComplaintRequestId(Long complaintRequestId) {
		this.complaintRequestId = complaintRequestId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
