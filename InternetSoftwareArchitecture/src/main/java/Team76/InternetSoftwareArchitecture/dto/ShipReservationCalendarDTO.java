package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.List;

public class ShipReservationCalendarDTO {

	private List<ShipReservationCalendarInformationDTO> shipReservations;
	private Date availabilityStart;
	private Date availabilityEnd;
	
	public ShipReservationCalendarDTO() {
		
	}

	public ShipReservationCalendarDTO(List<ShipReservationCalendarInformationDTO> shipReservations,
			Date availabilityStart, Date availabilityEnd) {
		super();
		this.shipReservations = shipReservations;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
	}

	public List<ShipReservationCalendarInformationDTO> getShipReservations() {
		return shipReservations;
	}

	public void setShipReservations(List<ShipReservationCalendarInformationDTO> shipReservations) {
		this.shipReservations = shipReservations;
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
