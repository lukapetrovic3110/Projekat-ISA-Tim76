package Team76.InternetSoftwareArchitecture.dto;

public class ComplaintRequestDTO {
	
	private String comment;
	private Long complaintEntityId;
	
	public ComplaintRequestDTO(String comment, Long complaintEntityId) {
		super();
		this.comment = comment;
		this.complaintEntityId = complaintEntityId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getComplaintEntityId() {
		return complaintEntityId;
	}

	public void setComplaintEntityId(Long complaintEntityId) {
		this.complaintEntityId = complaintEntityId;
	}
	
	
	
}
