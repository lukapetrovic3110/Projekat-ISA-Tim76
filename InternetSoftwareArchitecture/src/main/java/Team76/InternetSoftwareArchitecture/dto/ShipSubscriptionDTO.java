package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.ShipType;

public class ShipSubscriptionDTO {
	
	private Long id;
	private String name;
	private String address;
	private String description;
	private Integer capacity;
	private ShipType shipType;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	
	public ShipSubscriptionDTO() {
		
	}

	public ShipSubscriptionDTO(Long id, String name, String address, String description, Integer capacity,
			ShipType shipType, String ownerFirstName, String ownerLastName, String ownerEmail,
			String ownerPhoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.capacity = capacity;
		this.shipType = shipType;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
