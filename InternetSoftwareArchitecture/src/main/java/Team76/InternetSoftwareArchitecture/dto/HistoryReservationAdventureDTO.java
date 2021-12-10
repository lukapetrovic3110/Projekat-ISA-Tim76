package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationAdventureDTO {
	private String dateAndTime;
	private Integer duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfAdventure;
	private String fishingInstructor;
	private String description;
	private Integer maxNumberOfPersons;
	
	public HistoryReservationAdventureDTO() {
		
	}
	
	public HistoryReservationAdventureDTO(String dateAndTime, Integer duration, Double price, Integer numberOfPersons,
			String nameOfAdventure, String fishingInstructor, String description, Integer maxNumberOfPersons) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfAdventure = nameOfAdventure;
		this.fishingInstructor = fishingInstructor;
		this.description = description;
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

	public String getNameOfAdventure() {
		return nameOfAdventure;
	}

	public void setNameOfAdventure(String nameOfAdventure) {
		this.nameOfAdventure = nameOfAdventure;
	}

	public String getFishingInstructor() {
		return fishingInstructor;
	}

	public void setFishingInstructor(String fishingInstructor) {
		this.fishingInstructor = fishingInstructor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}	
	
}
