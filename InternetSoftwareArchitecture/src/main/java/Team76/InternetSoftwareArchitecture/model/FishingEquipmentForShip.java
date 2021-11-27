package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FishingEquipmentForShip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fishingEquipmentForShipId", unique = true, nullable = false)
	private Long fishingEquipmentForShipId;

	@Enumerated(EnumType.STRING)
	private FishingEquipmentForShipType fishingEquipmentForShipType;

	public FishingEquipmentForShip() {
	}

	public FishingEquipmentForShip(FishingEquipmentForShipType fishingEquipmentForShipType) {
		super();
		this.fishingEquipmentForShipType = fishingEquipmentForShipType;
	}

	public Long getFishingEquipmentForShipId() {
		return fishingEquipmentForShipId;
	}

	public void setFishingEquipmentForShipId(Long fishingEquipmentForShipId) {
		this.fishingEquipmentForShipId = fishingEquipmentForShipId;
	}

	public FishingEquipmentForShipType getFishingEquipmentForShipType() {
		return fishingEquipmentForShipType;
	}

	public void setFishingEquipmentForShipType(FishingEquipmentForShipType fishingEquipmentForShipType) {
		this.fishingEquipmentForShipType = fishingEquipmentForShipType;
	}

}
