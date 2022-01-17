package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationCottageDTO {
	
	private Long cottageId;
	private String dateAndTime;
	private Integer duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfCottage;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	private String cottageAddress;
	private Long ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private String ownerAddress;
	
	public HistoryReservationCottageDTO(Long cottageId, String dateAndTime, Integer duration, Double price,
			Integer numberOfPersons, String nameOfCottage, Integer numberOfRooms, Integer numberOfBedsPerRoom,
			String cottageAddress, Long ownerId, String ownerFirstName, String ownerLastName, String ownerEmail,
			String ownerPhoneNumber, String ownerAddress) {
		super();
		this.cottageId = cottageId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfCottage = nameOfCottage;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.cottageAddress = cottageAddress;
		this.ownerId = ownerId;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.ownerAddress = ownerAddress;
	}

	public Long getCottageId() {
		return cottageId;
	}

	public void setCottageId(Long cottageId) {
		this.cottageId = cottageId;
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

	public String getNameOfCottage() {
		return nameOfCottage;
	}

	public void setNameOfCottage(String nameOfCottage) {
		this.nameOfCottage = nameOfCottage;
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

	public String getCottageAddress() {
		return cottageAddress;
	}

	public void setCottageAddress(String cottageAddress) {
		this.cottageAddress = cottageAddress;
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
