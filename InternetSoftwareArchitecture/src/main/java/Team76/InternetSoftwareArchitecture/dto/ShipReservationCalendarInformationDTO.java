package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

public class ShipReservationCalendarInformationDTO {
	
	private Date reservationStartDate;
	private Date reservationEndDate;
	private String shipName;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	private ReservationStatus reservationStatus;
	
	public ShipReservationCalendarInformationDTO() {
		
	}

	public ShipReservationCalendarInformationDTO(Date reservationStartDate, Date reservationEndDate, String shipName,
			String clientFirstName, String clientLastName, String clientEmail, ReservationStatus reservationStatus) {
		super();
		this.reservationStartDate = reservationStartDate;
		this.reservationEndDate = reservationEndDate;
		this.shipName = shipName;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.clientEmail = clientEmail;
		this.reservationStatus = reservationStatus;
	}

	public Date getReservationStartDate() {
		return reservationStartDate;
	}

	public void setReservationStartDate(Date reservationStartDate) {
		this.reservationStartDate = reservationStartDate;
	}

	public Date getReservationEndDate() {
		return reservationEndDate;
	}

	public void setReservationEndDate(Date reservationEndDate) {
		this.reservationEndDate = reservationEndDate;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
}
