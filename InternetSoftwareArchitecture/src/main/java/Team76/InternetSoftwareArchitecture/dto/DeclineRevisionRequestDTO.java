package Team76.InternetSoftwareArchitecture.dto;

public class DeclineRevisionRequestDTO {
	
	private Long revisionRequestId;
	
	public DeclineRevisionRequestDTO() {
		
	}

	public DeclineRevisionRequestDTO(Long revisionRequestId) {
		super();
		this.revisionRequestId = revisionRequestId;
	}

	public Long getRevisionRequestId() {
		return revisionRequestId;
	}

	public void setRevisionRequestId(Long revisionRequestId) {
		this.revisionRequestId = revisionRequestId;
	}
	
}
