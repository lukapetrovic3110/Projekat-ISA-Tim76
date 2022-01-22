package Team76.InternetSoftwareArchitecture.dto;

public class FishingEquipmentForShipDTO {

	private String fishingEquipmentForShipType;
	
	public FishingEquipmentForShipDTO() {
		
	}

	public FishingEquipmentForShipDTO(String fishingEquipmentForShipType) {
		super();
		this.fishingEquipmentForShipType = fishingEquipmentForShipType;
	}

	public String getFishingEquipmentForShipType() {
		return fishingEquipmentForShipType;
	}

	public void setFishingEquipmentForShipType(String fishingEquipmentForShipType) {
		this.fishingEquipmentForShipType = fishingEquipmentForShipType;
	}
	
}
