package Team76.InternetSoftwareArchitecture.dto;

public class DeleteCottageDTO {

	private Long cottageId;
	
	public DeleteCottageDTO() {
		
	}

	public DeleteCottageDTO(Long cottageId) {
		super();
		this.cottageId = cottageId;
	}

	public Long getCottageId() {
		return cottageId;
	}

	public void setCottageId(Long cottageId) {
		this.cottageId = cottageId;
	}
	
}
