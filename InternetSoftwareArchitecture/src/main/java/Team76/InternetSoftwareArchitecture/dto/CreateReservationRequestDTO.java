package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

public class CreateReservationRequestDTO {
	
	private Long id;
	private Date reservationDateAndTime;
	private Integer duration;
	private Integer numberOfGuests;
	private Double reservationPrice;
	
	public CreateReservationRequestDTO() {
		
	}

	public CreateReservationRequestDTO(Long id, Date reservationDateAndTime, Integer duration, Integer numberOfGuests,
			Double reservationPrice) {
		super();
		this.id = id;
		this.reservationDateAndTime = reservationDateAndTime;
		this.duration = duration;
		this.numberOfGuests = numberOfGuests;
		this.reservationPrice = reservationPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReservationDateAndTime() {
		return reservationDateAndTime;
	}

	public void setReservationDateAndTime(Date reservationDateAndTime) {
		this.reservationDateAndTime = reservationDateAndTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(Integer numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public Double getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(Double reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	
}
