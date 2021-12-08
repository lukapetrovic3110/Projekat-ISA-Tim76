package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CottageDTO {

	private Long cottageId;
	private String name;
	private String description;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private Double longitude;
	private Double latitude;
	private Double rating;
	private Date availabilityStart;
	private Date availabilityEnd;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private HashMap<String, Double> priceList = new HashMap<String, Double>();
	private List<String> cottageRules;

	public CottageDTO() {

	}

	public Long getCottageId() {
		return cottageId;
	}

	public void setCottageId(Long cottageId) {
		this.cottageId = cottageId;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
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

	public HashMap<String, Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(HashMap<String, Double> priceList) {
		this.priceList = priceList;
	}

	public List<String> getCottageRules() {
		return cottageRules;
	}

	public void setCottageRules(List<String> cottageRules) {
		this.cottageRules = cottageRules;
	}

	public CottageDTO(Long cottageId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Date availabilityStart,
			Date availabilityEnd, Integer numberOfRooms, Integer numberOfBedsPerRoom, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber) {
		super();
		this.cottageId = cottageId;
		this.name = name;
		this.description = description;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rating = rating;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public CottageDTO(Long cottageId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Date availabilityStart,
			Date availabilityEnd, Integer numberOfRooms, Integer numberOfBedsPerRoom, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber, HashMap<String, Double> priceList,
			List<String> cottageRules) {
		super();
		this.cottageId = cottageId;
		this.name = name;
		this.description = description;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rating = rating;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.priceList = priceList;
		this.cottageRules = cottageRules;
	}

}
