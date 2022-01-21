package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

public class CottageReservationCalendarInformationDTO {

	private Date reservationStartDate;
	private Date reservationEndDate;
	private String cottageName;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	private ReservationStatus reservationStatus;
	
	public CottageReservationCalendarInformationDTO() {
		
	}

	public CottageReservationCalendarInformationDTO(Date reservationStartDate, Date reservationEndDate,
			String cottageName, String clientFirstName, String clientLastName, String clientEmail,
			ReservationStatus reservationStatus) {
		super();
		this.reservationStartDate = reservationStartDate;
		this.reservationEndDate = reservationEndDate;
		this.cottageName = cottageName;
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

	public String getCottageName() {
		return cottageName;
	}

	public void setCottageName(String cottageName) {
		this.cottageName = cottageName;
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
