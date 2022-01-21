package Team76.InternetSoftwareArchitecture.dto;

public class DeleteCottageReservationDTO {

	private Long cottageReservationId;
	
	public DeleteCottageReservationDTO() {
		
	}

	public DeleteCottageReservationDTO(Long cottageReservationId) {
		super();
		this.cottageReservationId = cottageReservationId;
	}

	public Long getCottageReservationId() {
		return cottageReservationId;
	}

	public void setCottageReservationId(Long cottageReservationId) {
		this.cottageReservationId = cottageReservationId;
	}
	
}
