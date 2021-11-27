package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FishingEquipmentForAdventure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fishingEquipmentForAdventureId", unique = true, nullable = false)
	private Long fishingEquipmentForAdventureId;

	@Enumerated(EnumType.STRING)
	private FishingEquipmentForAdventureType fishingEquipmentForAdventureType;

	public FishingEquipmentForAdventure() {

	}

	public FishingEquipmentForAdventure(FishingEquipmentForAdventureType fishingEquipmentForAdventureType) {
		super();

		this.fishingEquipmentForAdventureType = fishingEquipmentForAdventureType;
	}

	public Long getFishingEquipmentForAdventureId() {
		return fishingEquipmentForAdventureId;
	}

	public void setFishingEquipmentForAdventureId(Long fishingEquipmentForAdventureId) {
		this.fishingEquipmentForAdventureId = fishingEquipmentForAdventureId;
	}

	public FishingEquipmentForAdventureType getFishingEquipmentForAdventureType() {
		return fishingEquipmentForAdventureType;
	}

	public void setFishingEquipmentForAdventureType(FishingEquipmentForAdventureType fishingEquipmentForAdventureType) {
		this.fishingEquipmentForAdventureType = fishingEquipmentForAdventureType;
	}

}
