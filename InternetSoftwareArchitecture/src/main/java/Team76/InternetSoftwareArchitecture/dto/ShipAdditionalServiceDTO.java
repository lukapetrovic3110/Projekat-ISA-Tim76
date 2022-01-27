package Team76.InternetSoftwareArchitecture.dto;

public class ShipAdditionalServiceDTO {

	private String shipAdditionalServiceType;
	
	public ShipAdditionalServiceDTO() {
		
	}

	public ShipAdditionalServiceDTO(String shipAdditionalServiceType) {
		super();
		this.shipAdditionalServiceType = shipAdditionalServiceType;
	}

	public String getShipAdditionalServiceType() {
		return shipAdditionalServiceType;
	}

	public void setShipAdditionalServiceType(String shipAdditionalServiceType) {
		this.shipAdditionalServiceType = shipAdditionalServiceType;
	}
	
}
