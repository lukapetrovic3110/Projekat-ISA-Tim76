package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

public class CottageReservationInformationDTO {

	private Long cottageReservationId;
	private Date dateAndTime;
	private Integer duration;
	private Integer maxNumberOfPersons;
	private Double price;
	private String cottageName;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	private CottageReservationClientInformationDTO cottageReservationClientInformationDTO;
	private ReservationStatus reservationStatus;
	
	public CottageReservationInformationDTO() {
		
	}

	public CottageReservationInformationDTO(Long cottageReservationId, Date dateAndTime, Integer duration,
			Integer maxNumberOfPersons, Double price, String cottageName, Integer numberOfRooms,
			Integer numberOfBedsPerRoom, CottageReservationClientInformationDTO cottageReservationClientInformationDTO,
			ReservationStatus reservationStatus) {
		super();
		this.cottageReservationId = cottageReservationId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.cottageName = cottageName;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.cottageReservationClientInformationDTO = cottageReservationClientInformationDTO;
		this.reservationStatus = reservationStatus;
	}

	public Long getCottageReservationId() {
		return cottageReservationId;
	}

	public void setCottageReservationId(Long cottageReservationId) {
		this.cottageReservationId = cottageReservationId;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCottageName() {
		return cottageName;
	}

	public void setCottageName(String cottageName) {
		this.cottageName = cottageName;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getNumberOfBedsPerRoom() {
		return numberOfBedsPerRoom;
	}

	public void setNumberOfBedsPerRoom(Integer numberOfBedsPerRoom) {
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
	}

	public CottageReservationClientInformationDTO getCottageReservationClientInformationDTO() {
		return cottageReservationClientInformationDTO;
	}

	public void setCottageReservationClientInformationDTO(
			CottageReservationClientInformationDTO cottageReservationClientInformationDTO) {
		this.cottageReservationClientInformationDTO = cottageReservationClientInformationDTO;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
		
}
