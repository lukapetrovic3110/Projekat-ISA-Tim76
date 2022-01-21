package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

public class DeleteCottageReservationDTO {

	private Date cottageReservationDateAndTime;
	
	public DeleteCottageReservationDTO() {
		
	}

	public DeleteCottageReservationDTO(Date cottageReservationDateAndTime) {
		super();
		this.cottageReservationDateAndTime = cottageReservationDateAndTime;
	}

	public Date getCottageReservationDateAndTime() {
		return cottageReservationDateAndTime;
	}

	public void setCottageReservationDateAndTime(Date cottageReservationDateAndTime) {
		this.cottageReservationDateAndTime = cottageReservationDateAndTime;
	}
	
}
