package Team76.InternetSoftwareArchitecture.dto;

public class AnswerProfileDeleteRequestDTO {
	
	private Long profileDeleteRequestId;
	private String email;
	private String comment;
	
	public AnswerProfileDeleteRequestDTO() {
	
	}

	public AnswerProfileDeleteRequestDTO(String email, String comment, Long profileDeleteRequestId) {
		this.email = email;
		this.comment = comment;
		this.profileDeleteRequestId = profileDeleteRequestId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getProfileDeleteRequestId() {
		return profileDeleteRequestId;
	}

	public void setProfileDeleteRequestId(Long profileDeleteRequestId) {
		this.profileDeleteRequestId = profileDeleteRequestId;
	}
	
}
