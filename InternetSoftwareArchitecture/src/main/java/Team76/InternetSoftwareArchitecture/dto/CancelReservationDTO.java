package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.ReservationType;

public class CancelReservationDTO {
	
	private Long reservationId;
	private ReservationType reservationType;
	
	public CancelReservationDTO() {
		
	}
	
	public CancelReservationDTO(Long reservationId, ReservationType reservationType) {
		this.reservationId = reservationId;
		this.reservationType = reservationType;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}
}
