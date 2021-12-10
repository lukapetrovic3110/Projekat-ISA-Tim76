package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.ReservationType;

public class ScheduledReservationDTO {
	
	private Long reservationId;
	private ReservationType reservationType;
	private String dateAndTime;
	private String duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfReservation;
	
	public ScheduledReservationDTO() {
		
	}

	public ScheduledReservationDTO(Long reservationId, ReservationType reservationType, String dateAndTime,
			String duration, Double price, Integer numberOfPersons, String nameOfReservation) {
		super();
		this.reservationId = reservationId;
		this.reservationType = reservationType;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfReservation = nameOfReservation;
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

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public String getNameOfReservation() {
		return nameOfReservation;
	}

	public void setNameOfReservation(String nameOfReservation) {
		this.nameOfReservation = nameOfReservation;
	}

}
