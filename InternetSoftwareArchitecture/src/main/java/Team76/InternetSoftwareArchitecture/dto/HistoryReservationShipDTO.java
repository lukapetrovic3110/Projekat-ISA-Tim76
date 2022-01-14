package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationShipDTO {
	
	private Long shipId;
	private String dateAndTime; 
	private Integer duration; 
	private Double price;
	private Integer numberOfPersons; 
	private String nameOfShip;
	private Integer maxNumberOfPersons; 
	private String shipAddress;
	private Long ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private String ownerAddress;
	
	public HistoryReservationShipDTO() {
	
	}

	public HistoryReservationShipDTO(Long shipId, String dateAndTime, Integer duration, Double price,
			Integer numberOfPersons, String nameOfShip, Integer maxNumberOfPersons, String shipAddress, Long ownerId,
			String ownerFirstName, String ownerLastName, String ownerEmail, String ownerPhoneNumber,
			String ownerAddress) {
		super();
		this.shipId = shipId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfShip = nameOfShip;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.shipAddress = shipAddress;
		this.ownerId = ownerId;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.ownerAddress = ownerAddress;
	}

	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipId) {
		this.shipId = shipId;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
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

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public String getNameOfShip() {
		return nameOfShip;
	}

	public void setNameOfShip(String nameOfShip) {
		this.nameOfShip = nameOfShip;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

}
