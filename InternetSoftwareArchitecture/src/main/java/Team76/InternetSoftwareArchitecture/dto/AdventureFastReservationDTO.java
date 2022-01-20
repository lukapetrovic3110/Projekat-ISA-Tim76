package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.Set;

import Team76.InternetSoftwareArchitecture.model.AdventureAdditionalService;
import Team76.InternetSoftwareArchitecture.model.FishingEquipmentForAdventure;

public class AdventureFastReservationDTO {
	
	private Long adventureFastReservationId;
	private Date dateAndTime;
	private Integer duration;
	private Integer maxNumberOfPersons;
	private Set<AdventureAdditionalService> adventureAdditionalServices;
	private Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure;
	private Double price;
	private Integer discountPercentage;
	
	public AdventureFastReservationDTO() {
		
	}

	public AdventureFastReservationDTO(Long adventureFastReservationId, Date dateAndTime, Integer duration,
			Integer maxNumberOfPersons, Set<AdventureAdditionalService> adventureAdditionalServices,
			Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure, Double price, Integer discountPercentage) {
		super();
		this.adventureFastReservationId = adventureFastReservationId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.adventureAdditionalServices = adventureAdditionalServices;
		this.fishingEquipmentForAdventure = fishingEquipmentForAdventure;
		this.price = price;
		this.discountPercentage = discountPercentage;
	}

	public Long getAdventureFastReservationId() {
		return adventureFastReservationId;
	}

	public void setAdventureFastReservationId(Long adventureFastReservationId) {
		this.adventureFastReservationId = adventureFastReservationId;
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

	public Set<AdventureAdditionalService> getAdventureAdditionalServices() {
		return adventureAdditionalServices;
	}

	public void setAdventureAdditionalServices(Set<AdventureAdditionalService> adventureAdditionalServices) {
		this.adventureAdditionalServices = adventureAdditionalServices;
	}

	public Set<FishingEquipmentForAdventure> getFishingEquipmentForAdventure() {
		return fishingEquipmentForAdventure;
	}

	public void setFishingEquipmentForAdventure(Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure) {
		this.fishingEquipmentForAdventure = fishingEquipmentForAdventure;
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
