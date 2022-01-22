package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.List;

import Team76.InternetSoftwareArchitecture.model.Address;

public class AddCottageDTO {

	private String name;
	private String description;
	private Address address;
	private Integer numberOfRooms;
	private Integer numberOfBedsPerRoom;
	private Double pricePerDay;
	private Long cottageOwnerId;
	private Date availabilityStart;
	private Date availabilityEnd;
	private List<String> priceList;
	private List<String> cottageRules;

	public AddCottageDTO() {

	}

	public AddCottageDTO(String name, String description, Address address, Integer numberOfRooms,
			Integer numberOfBedsPerRoom, Double pricePerDay, Long cottageOwnerId, Date availabilityStart,
			Date availabilityEnd, List<String> priceList, List<String> cottageRules) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.pricePerDay = pricePerDay;
		this.cottageOwnerId = cottageOwnerId;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.priceList = priceList;
		this.cottageRules = cottageRules;
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

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Long getCottageOwnerId() {
		return cottageOwnerId;
	}

	public void setCottageOwnerId(Long cottageOwnerId) {
		this.cottageOwnerId = cottageOwnerId;
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

	public List<String> getCottageRules() {
		return cottageRules;
	}

	public void setCottageRules(List<String> cottageRules) {
		this.cottageRules = cottageRules;
	}

}
