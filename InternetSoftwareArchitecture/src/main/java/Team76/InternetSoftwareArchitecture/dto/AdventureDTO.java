package Team76.InternetSoftwareArchitecture.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AdventureDTO {
	private Long adventureId;
	private String name;
	private String description;
	private Double rating;
	private String instructorBiography;
	private Integer maxNumberOfPersons;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private Double longitude;
	private Double latitude;
	private Date availabilityStart;
	private Date availabilityEnd;
	private String instructorFirstName;
	private String instructorLastName;
	private String instructorEmail;
	private String instructorPhoneNumber;
	private Long instructorId;
	private HashMap<String, Double> priceList = new HashMap<String, Double>();
	private List<String> adventureRules = new ArrayList<String>();
	private List<String> adventureImages = new ArrayList<String>();

	public AdventureDTO() {

	}

	public AdventureDTO(Long adventureId, String name, String description, Double rating, String instructorBiography,
			Integer maxNumberOfPersons, String street, String streetNumber, String city, String country,
			Double longitude, Double latitude, Date availabilityStart, Date availabilityEnd, String instructorFirstName,
			String instructorLastName, String instructorEmail, String instructorPhoneNumber, Long instructorId, List<String> adventureImages) {
		super();
		this.adventureId = adventureId;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.instructorBiography = instructorBiography;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.instructorFirstName = instructorFirstName;
		this.instructorLastName = instructorLastName;
		this.instructorEmail = instructorEmail;
		this.instructorPhoneNumber = instructorPhoneNumber;
		this.instructorId = instructorId;
		this.adventureImages = adventureImages;
	}

	public Long getAdventureId() {
		return adventureId;
	}

	public void setAdventureId(Long adventureId) {
		this.adventureId = adventureId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getInstructorBiography() {
		return instructorBiography;
	}

	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getAvailabilityStart() {
		return availabilityStart;
	}

	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}

	public Date getAvailabilityEnd() {
		return availabilityEnd;
	}

	public void setAvailabilityEnd(Date availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}

	public String getInstructorFirstName() {
		return instructorFirstName;
	}

	public void setInstructorFirstName(String instructorFirstName) {
		this.instructorFirstName = instructorFirstName;
	}

	public String getInstructorLastName() {
		return instructorLastName;
	}

	public void setInstructorLastName(String instructorLastName) {
		this.instructorLastName = instructorLastName;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

	public String getInstructorPhoneNumber() {
		return instructorPhoneNumber;
	}

	public void setInstructorPhoneNumber(String instructorPhoneNumber) {
		this.instructorPhoneNumber = instructorPhoneNumber;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public HashMap<String, Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(HashMap<String, Double> priceList) {
		this.priceList = priceList;
	}

	public List<String> getAdventureRules() {
		return adventureRules;
	}

	public void setAdventureRules(List<String> adventureRules) {
		this.adventureRules = adventureRules;
	}

	public List<String> getAdventureImages() {
		return adventureImages;
	}

	public void setAdventureImages(List<String> adventureImages) {
		this.adventureImages = adventureImages;
	}

}
