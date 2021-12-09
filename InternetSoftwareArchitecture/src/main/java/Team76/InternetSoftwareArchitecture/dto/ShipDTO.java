package Team76.InternetSoftwareArchitecture.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private List<String> navigationEquipment = new ArrayList<String>();
	private HashMap<String, Double> priceList = new HashMap<String, Double>();
	private List<String> shipRules = new ArrayList<String>();
	private List<String> shipImages = new ArrayList<String>();

	public ShipDTO() {

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

	public List<String> getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(List<String> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public HashMap<String, Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(HashMap<String, Double> priceList) {
		this.priceList = priceList;
	}

	public List<String> getShipRules() {
		return shipRules;
	}

	public void setShipRules(List<String> shipRules) {
		this.shipRules = shipRules;
	}

	public List<String> getShipImages() {
		return shipImages;
	}

	public void setShipImages(List<String> shipImages) {
		this.shipImages = shipImages;
	}

	public ShipDTO(Long shipId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Double length, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, ShipType shipType, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber, List<String> shipImages) {
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
	}

	public ShipDTO(Long shipId, String name, String description, String street, String streetNumber, String city,
			String country, Double longitude, Double latitude, Double rating, Double length, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, ShipType shipType, String ownerFirstName,
			String ownerLastName, String ownerEmail, String ownerPhoneNumber, List<String> navigationEquipment,
			HashMap<String, Double> priceList, List<String> shipRules, List<String> shipImages) {
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
		this.navigationEquipment = navigationEquipment;
		this.priceList = priceList;
		this.shipRules = shipRules;
		this.shipImages = shipImages;
	}

}
