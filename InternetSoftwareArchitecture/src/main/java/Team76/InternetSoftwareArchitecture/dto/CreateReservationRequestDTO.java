package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

public class CreateReservationRequestDTO {
	
	private Long cottageId;
	private Date cottageReservationDateAndTime;
	private Integer duration;
	private Integer numberOfGuests;
	private Double reservationPrice;
	
	public CreateReservationRequestDTO() {
		
	}

	public CreateReservationRequestDTO(Long cottageId, Date cottageReservationDateAndTime, Integer duration,
			Integer numberOfGuests, Double reservationPrice) {
		super();
		this.cottageId = cottageId;
		this.cottageReservationDateAndTime = cottageReservationDateAndTime;
		this.duration = duration;
		this.numberOfGuests = numberOfGuests;
		this.reservationPrice = reservationPrice;
	}

	public Long getCottageId() {
		return cottageId;
	}

	public void setCottageId(Long cottageId) {
		this.cottageId = cottageId;
	}

	public Date getCottageReservationDateAndTime() {
		return cottageReservationDateAndTime;
	}

	public void setCottageReservationDateAndTime(Date cottageReservationDateAndTime) {
		this.cottageReservationDateAndTime = cottageReservationDateAndTime;
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
