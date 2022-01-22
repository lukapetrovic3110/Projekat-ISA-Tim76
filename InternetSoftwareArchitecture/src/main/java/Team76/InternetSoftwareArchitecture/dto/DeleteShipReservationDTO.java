package Team76.InternetSoftwareArchitecture.dto;

public class DeleteShipReservationDTO {

	private Long shipReservationId;
	
	public DeleteShipReservationDTO() {
		
	}

	public DeleteShipReservationDTO(Long shipReservationId) {
		super();
		this.shipReservationId = shipReservationId;
	}

	public Long getShipReservationId() {
		return shipReservationId;
	}

	public void setShipReservationId(Long shipReservationId) {
		this.shipReservationId = shipReservationId;
	}
	
}
