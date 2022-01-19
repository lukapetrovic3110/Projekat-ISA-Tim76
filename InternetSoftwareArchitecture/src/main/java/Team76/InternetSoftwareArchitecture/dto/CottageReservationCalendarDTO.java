package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.List;

public class CottageReservationCalendarDTO {
	
	private List<CottageReservationCalendarInformationDTO> cottageReservations;
	private Date availabilityStart;
	private Date availabilityEnd;
	
	public CottageReservationCalendarDTO() {
		
	}

	public CottageReservationCalendarDTO(List<CottageReservationCalendarInformationDTO> cottageReservations,
			Date availabilityStart, Date availabilityEnd) {
		super();
		this.cottageReservations = cottageReservations;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
	}

	public List<CottageReservationCalendarInformationDTO> getCottageReservations() {
		return cottageReservations;
	}

	public void setCottageReservations(List<CottageReservationCalendarInformationDTO> cottageReservations) {
		this.cottageReservations = cottageReservations;
	}

	public Date getAvailabilityStart() {
		return availabilityStart;
	}

	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}

	public Date getAvailabilityEnd() {
		return availabilityEnd;
	}

	public void setAvailabilityEnd(Date availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}
	
}
