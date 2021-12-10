package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationShipDTO {
	
	private String dateAndTime;
	private Integer duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfShip;
	private Integer maxNumberOfPersons;
	
	public HistoryReservationShipDTO() {
	
	}
	
	public HistoryReservationShipDTO(String dateAndTime, Integer duration, Double price, Integer numberOfPersons,
			String nameOfShip, Integer maxNumberOfPersons) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfShip = nameOfShip;
		this.maxNumberOfPersons = maxNumberOfPersons;
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

	
	
}
