package Team76.InternetSoftwareArchitecture.dto;

public class HistoryReservationAdventureDTO {
	
	private Long adventureId;
	private String dateAndTime;
	private Integer duration;
	private Double price;
	private Integer numberOfPersons;
	private String nameOfAdventure;
	private String description;
	private Integer maxNumberOfPersons;
	private String adventureAddress;
	private Long fishingInstructorId;
	private String fishingInstructorFirstName;
	private String fishingInstructorLastName;
	private String fishingInstructorEmail;
	private String fishingInstructorPhoneNumber;
	private String fishingInstructorBiography;
	private String fishingInstructorAddress;
	
	public HistoryReservationAdventureDTO() {
		
	}
	
	public HistoryReservationAdventureDTO(Long adventureId, String dateAndTime, Integer duration, Double price,
			Integer numberOfPersons, String nameOfAdventure, String description, Integer maxNumberOfPersons,
			String adventureAddress, Long fishingInstructorId, String fishingInstructorFirstName,
			String fishingInstructorLastName, String fishingInstructorEmail, String fishingInstructorPhoneNumber,
			String fishingInstructorBiography, String fishingInstructorAddress) {
		super();
		this.adventureId = adventureId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.price = price;
		this.numberOfPersons = numberOfPersons;
		this.nameOfAdventure = nameOfAdventure;
		this.description = description;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.adventureAddress = adventureAddress;
		this.fishingInstructorId = fishingInstructorId;
		this.fishingInstructorFirstName = fishingInstructorFirstName;
		this.fishingInstructorLastName = fishingInstructorLastName;
		this.fishingInstructorEmail = fishingInstructorEmail;
		this.fishingInstructorPhoneNumber = fishingInstructorPhoneNumber;
		this.fishingInstructorBiography = fishingInstructorBiography;
		this.fishingInstructorAddress = fishingInstructorAddress;
	}
	
	public Long getAdventureId() {
		return adventureId;
	}

	public void setAdventureId(Long adventureId) {
		this.adventureId = adventureId;
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

	public Long getFishingInstructorId() {
		return fishingInstructorId;
	}

	public void setFishingInstructorId(Long fishingInstructorId) {
		this.fishingInstructorId = fishingInstructorId;
	}

	public String getFishingInstructorFirstName() {
		return fishingInstructorFirstName;
	}

	public void setFishingInstructorFirstName(String fishingInstructorFirstName) {
		this.fishingInstructorFirstName = fishingInstructorFirstName;
	}

	public String getFishingInstructorLastName() {
		return fishingInstructorLastName;
	}

	public void setFishingInstructorLastName(String fishingInstructorLastName) {
		this.fishingInstructorLastName = fishingInstructorLastName;
	}

	public String getFishingInstructorEmail() {
		return fishingInstructorEmail;
	}

	public void setFishingInstructorEmail(String fishingInstructorEmail) {
		this.fishingInstructorEmail = fishingInstructorEmail;
	}

	public String getFishingInstructorPhoneNumber() {
		return fishingInstructorPhoneNumber;
	}

	public void setFishingInstructorPhoneNumber(String fishingInstructorPhoneNumber) {
		this.fishingInstructorPhoneNumber = fishingInstructorPhoneNumber;
	}

	public String getFishingInstructorBiography() {
		return fishingInstructorBiography;
	}

	public void setFishingInstructorBiography(String fishingInstructorBiography) {
		this.fishingInstructorBiography = fishingInstructorBiography;
	}

	public String getAdventureAddress() {
		return adventureAddress;
	}

	public void setAdventureAddress(String adventureAddress) {
		this.adventureAddress = adventureAddress;
	}

	public String getFishingInstructorAddress() {
		return fishingInstructorAddress;
	}

	public void setFishingInstructorAddress(String fishingInstructorAddress) {
		this.fishingInstructorAddress = fishingInstructorAddress;
	}
	
}
