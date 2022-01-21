package Team76.InternetSoftwareArchitecture.dto;

public class ScheduleFastReservationDTO {
	
	private Long fastReservationId;
	
	public ScheduleFastReservationDTO() {
	
	}

	public ScheduleFastReservationDTO(Long fastReservationId) {
		super();
		this.fastReservationId = fastReservationId;
	}

	public Long getFastReservationId() {
		return fastReservationId;
	}

	public void setFastReservationId(Long fastReservationId) {
		this.fastReservationId = fastReservationId;
	}
	
}
