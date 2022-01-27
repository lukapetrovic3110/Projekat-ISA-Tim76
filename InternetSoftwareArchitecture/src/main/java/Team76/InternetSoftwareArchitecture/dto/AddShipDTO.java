package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.List;

import Team76.InternetSoftwareArchitecture.model.Address;

public class AddShipDTO {

	private String name;
	private String description;
	private Address address;
	private Double shipLength;
	private Integer engineNumber;
	private Integer enginePower;
	private Integer maxSpeed;
	private Integer capacity;
	private String shipType;
	private List<Integer> navigationEquipmentIndices;
	private Double pricePerHour;
	private Long shipOwnerId;
	private Date availabilityStart;
	private Date availabilityEnd;
	private List<String> priceList;
	private List<String> shipRules;
	private String shipReservationCancelTerm;
	
	public AddShipDTO() {
		
	}

	public AddShipDTO(String name, String description, Address address, Double shipLength, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, String shipType,
			List<Integer> navigationEquipmentIndices, Double pricePerHour, Long shipOwnerId, Date availabilityStart,
			Date availabilityEnd, List<String> priceList, List<String> shipRules, String shipReservationCancelTerm) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.shipLength = shipLength;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.shipType = shipType;
		this.navigationEquipmentIndices = navigationEquipmentIndices;
		this.pricePerHour = pricePerHour;
		this.shipOwnerId = shipOwnerId;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.priceList = priceList;
		this.shipRules = shipRules;
		this.shipReservationCancelTerm = shipReservationCancelTerm;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Double getShipLength() {
		return shipLength;
	}

	public void setShipLength(Double shipLength) {
		this.shipLength = shipLength;
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

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public List<Integer> getNavigationEquipmentIndices() {
		return navigationEquipmentIndices;
	}

	public void setNavigationEquipmentIndices(List<Integer> navigationEquipmentIndices) {
		this.navigationEquipmentIndices = navigationEquipmentIndices;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Long getShipOwnerId() {
		return shipOwnerId;
	}

	public void setShipOwnerId(Long shipOwnerId) {
		this.shipOwnerId = shipOwnerId;
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

	public List<String> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String> priceList) {
		this.priceList = priceList;
	}

	public List<String> getShipRules() {
		return shipRules;
	}

	public void setShipRules(List<String> shipRules) {
		this.shipRules = shipRules;
	}

	public String getShipReservationCancelTerm() {
		return shipReservationCancelTerm;
	}

	public void setShipReservationCancelTerm(String shipReservationCancelTerm) {
		this.shipReservationCancelTerm = shipReservationCancelTerm;
	}
	
}
