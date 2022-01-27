package Team76.InternetSoftwareArchitecture.dto;

public class ShipNavigationEquipmentDTO {
	
	private String shipNavigationEquipmentType;
	
	public ShipNavigationEquipmentDTO() {
		
	}

	public ShipNavigationEquipmentDTO(String shipNavigationEquipmentType) {
		super();
		this.shipNavigationEquipmentType = shipNavigationEquipmentType;
	}

	public String getShipNavigationEquipmentType() {
		return shipNavigationEquipmentType;
	}

	public void setShipNavigationEquipmentType(String shipNavigationEquipmentType) {
		this.shipNavigationEquipmentType = shipNavigationEquipmentType;
	}
	
}
