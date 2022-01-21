package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.RevisionType;

public class AcceptRevisionRequestDTO {
	
	private Long revisionRequestId;
	private String clientEmail;
	private String userEmail;
	private RevisionType revisionType;
	
	public AcceptRevisionRequestDTO() {
	
	}

	public AcceptRevisionRequestDTO(Long revisionRequestId, String clientEmail, String userEmail,
			RevisionType revisionType) {
		super();
		this.revisionRequestId = revisionRequestId;
		this.clientEmail = clientEmail;
		this.userEmail = userEmail;
		this.revisionType = revisionType;
	}

	public Long getRevisionRequestId() {
		return revisionRequestId;
	}

	public void setRevisionRequestId(Long revisionRequestId) {
		this.revisionRequestId = revisionRequestId;
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


	public RevisionType getRevisionType() {
		return revisionType;
	}


	public void setRevisionType(RevisionType revisionType) {
		this.revisionType = revisionType;
	}

}
