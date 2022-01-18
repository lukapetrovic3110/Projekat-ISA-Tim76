package Team76.InternetSoftwareArchitecture.dto;

public class CottageAdditionalServiceDTO {

	private String cottageAdditionalServiceType;
	
	public CottageAdditionalServiceDTO() {
		
	}

	public CottageAdditionalServiceDTO(String cottageAdditionalServiceType) {
		super();
		this.cottageAdditionalServiceType = cottageAdditionalServiceType;
	}

	public String getCottageAdditionalServiceType() {
		return cottageAdditionalServiceType;
	}

	public void setCottageAdditionalServiceType(String cottageAdditionalServiceType) {
		this.cottageAdditionalServiceType = cottageAdditionalServiceType;
	}
	
}
