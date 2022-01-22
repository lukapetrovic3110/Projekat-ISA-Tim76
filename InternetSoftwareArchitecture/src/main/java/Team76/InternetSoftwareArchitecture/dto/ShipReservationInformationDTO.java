package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;

import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

public class ShipReservationInformationDTO {
	
	private Long shipReservationId;
	private Date dateAndTime;
	private Integer duration;
	private Double price;
	private String shipName;
	private Integer capacity;
	private Integer engineNumber;
	private Integer enginePower;
	private ShipReservationClientInformationDTO shipReservationClientInformationDTO;
	private ReservationStatus reservationStatus;
	
	public ShipReservationInformationDTO() {
		
	}
	
	public ShipReservationInformationDTO(Long shipReservationId, Date dateAndTime, Integer duration, Double price,
			String shipName, Integer capacity, Integer engineNumber, Integer enginePower,
			ShipReservationClientInformationDTO shipReservationClientInformationDTO,
			ReservationStatus reservationStatus) {
		super();
		this.shipReservationId = shipReservationId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.shipName = shipName;
		this.capacity = capacity;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.shipReservationClientInformationDTO = shipReservationClientInformationDTO;
		this.reservationStatus = reservationStatus;
	}

	public Long getShipReservationId() {
		return shipReservationId;
	}

	public void setShipReservationId(Long shipReservationId) {
		this.shipReservationId = shipReservationId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(Integer engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public ShipReservationClientInformationDTO getShipReservationClientInformationDTO() {
		return shipReservationClientInformationDTO;
	}

	public void setShipReservationClientInformationDTO(
			ShipReservationClientInformationDTO shipReservationClientInformationDTO) {
		this.shipReservationClientInformationDTO = shipReservationClientInformationDTO;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}	
	
}
