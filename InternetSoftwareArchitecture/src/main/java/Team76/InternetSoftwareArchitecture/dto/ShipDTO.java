package Team76.InternetSoftwareArchitecture.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Team76.InternetSoftwareArchitecture.model.PriceList;
import Team76.InternetSoftwareArchitecture.model.Rule;
import Team76.InternetSoftwareArchitecture.model.ShipType;

public class ShipDTO {
	private Long shipId;
	private String name;
	private String description;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private Double longitude;
	private Double latitude;
	private Double rating;
	private Double price;
	private Date availabilityStart;
	private Date availabilityEnd;
	private Double length;
	private Integer engineNumber;
	private Integer enginePower;
	private Integer maxSpeed;
	private Integer capacity;
	private ShipType shipType;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private PriceList priceList = new PriceList();
	private Set<Rule> shipRules = new HashSet<Rule>();
	private List<String> shipImages = new ArrayList<String>();

	public ShipDTO() {

	}

	public ShipDTO(Long shipId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Double price, Double length, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, ShipType shipType, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber, List<String> shipImages,
			Date availabilityStart, Date availabilityEnd, Set<Rule> shipRules) {
		super();
		this.shipId = shipId;
		this.name = name;
		this.description = description;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rating = rating;
		this.price = price;
		this.length = length;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.shipType = shipType;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.shipImages = shipImages;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.shipRules = shipRules;
	}
	
	public ShipDTO(Long shipId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Double price, Double length, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, ShipType shipType, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber, List<String> shipImages,
			Date availabilityStart, Date availabilityEnd, Set<Rule> shipRules,  PriceList priceList) {
		super();
		this.shipId = shipId;
		this.name = name;
		this.description = description;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rating = rating;
		this.price = price;
		this.length = length;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.shipType = shipType;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.shipImages = shipImages;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.shipRules = shipRules;
		this.priceList = priceList;
	}
	
	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipId) {
		this.shipId = shipId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
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

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
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

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Set<Rule> getShipRules() {
		return shipRules;
	}

	public void setShipRules(Set<Rule> shipRules) {
		this.shipRules = shipRules;
	}

	public List<String> getShipImages() {
		return shipImages;
	}

	public void setShipImages(List<String> shipImages) {
		this.shipImages = shipImages;
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

}
