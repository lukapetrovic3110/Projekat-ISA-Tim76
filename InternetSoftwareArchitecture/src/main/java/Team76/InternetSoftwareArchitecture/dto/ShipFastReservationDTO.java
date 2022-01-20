package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.Set;

import Team76.InternetSoftwareArchitecture.model.FishingEquipmentForShip;
import Team76.InternetSoftwareArchitecture.model.ShipAdditionalService;

public class ShipFastReservationDTO {
	
	private Long shipFastReservationId;
	private Date dateAndTime;
	private Integer duration;
	private Integer maxNumberOfPersons;
	private Set<ShipAdditionalService> shipAdditionalServices;
	private Set<FishingEquipmentForShip> fishingEquipmentForShip;
	private Double price;
	private Integer discountPercentage;
	
	public ShipFastReservationDTO() {
		
	}

	public ShipFastReservationDTO(Long shipFastReservationId, Date dateAndTime, Integer duration,
			Integer maxNumberOfPersons, Set<ShipAdditionalService> shipAdditionalServices,
			Set<FishingEquipmentForShip> fishingEquipmentForShip, Double price, Integer discountPercentage) {
		super();
		this.shipFastReservationId = shipFastReservationId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.shipAdditionalServices = shipAdditionalServices;
		this.fishingEquipmentForShip = fishingEquipmentForShip;
		this.price = price;
		this.discountPercentage = discountPercentage;
	}

	public Long getShipFastReservationId() {
		return shipFastReservationId;
	}

	public void setShipFastReservationId(Long shipFastReservationId) {
		this.shipFastReservationId = shipFastReservationId;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public Set<ShipAdditionalService> getShipAdditionalServices() {
		return shipAdditionalServices;
	}

	public void setShipAdditionalServices(Set<ShipAdditionalService> shipAdditionalServices) {
		this.shipAdditionalServices = shipAdditionalServices;
	}

	public Set<FishingEquipmentForShip> getFishingEquipmentForShip() {
		return fishingEquipmentForShip;
	}

	public void setFishingEquipmentForShip(Set<FishingEquipmentForShip> fishingEquipmentForShip) {
		this.fishingEquipmentForShip = fishingEquipmentForShip;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
}
