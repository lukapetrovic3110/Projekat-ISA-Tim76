package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationCottageDTO {
	
	private String dateAndTime;
	private Integer duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfCottage;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	
	public HistoryReservationCottageDTO() {
		
	}

	public HistoryReservationCottageDTO(String dateAndTime, Integer duration, Double price, Integer numberOfPersons,
			String nameOfCottage, Integer numberOfRooms, Integer numberOfBedsPerRoom) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfCottage = nameOfCottage;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
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

}
